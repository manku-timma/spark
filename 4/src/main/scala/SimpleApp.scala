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
    val dstream = sc.textFileStream("datadirectory")
    dstream.print()
    sc.start()
    sc.awaitTermination()
  }
}
