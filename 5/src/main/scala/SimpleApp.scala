import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object SimpleApp {
  def main(args: Array[String]) {
    val sc = new StreamingContext(
        "local[2]",
        "Processing a stream",
        Seconds(5),
        "",
        List("target/scala-2.10/simple-project_2.10-1.0.jar"))
    val dstream = sc.socketTextStream("127.0.0.1", 9999)
    // Print word count.
    dstream.flatMap(_.split(" ")).map(x => (x,1)).reduceByKey(_ + _).print
    sc.start()
    sc.awaitTermination()
  }
}
