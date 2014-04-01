import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.Logging
import org.apache.log4j.{Level, Logger}

object SimpleApp {
  def updateFunc(values: Seq[Int], runningCount: Option[Int]):
      Option[Int] = {
    val newCount = values.foldLeft(0)(_ + _)
    val oldCount = runningCount.getOrElse(0)
    Some(newCount + oldCount)
  }

  def main(args: Array[String]) {
    val sc = new StreamingContext(
        "local[2]",
        "Processing a stream statefully",
        Seconds(10),
        "",
        List("target/scala-2.10/simple-project_2.10-1.0.jar"))
    // This is crucial to the stateful word counting. Otherwise you will
    // hit an exception that the checkpoint directory is unset and the
    // network input will just get queued.
    sc.checkpoint("log")
    val dstream = sc.socketTextStream("127.0.0.1", 9999)

    val lineCounts = dstream.map(x => (x, 1)).reduceByKey(_ + _)
    val runningLineCounts = lineCounts.updateStateByKey[Int](updateFunc _)
    lineCounts.print
    runningLineCounts.print

    val wordCounts = dstream.flatMap(_.split(" ")).map(x => (x,1))
        .reduceByKey(_ + _)
    val runningCounts = wordCounts.updateStateByKey[Int](updateFunc _)
    wordCounts.print
    runningCounts.print

    sc.start()
    sc.awaitTermination()
  }
}
