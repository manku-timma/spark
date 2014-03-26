import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object SimpleApp {
  def main(args: Array[String]) {
    val filename = "../README.md"
    val sc = new SparkContext(
        "local",
        "Reading a file",
        "",
        List("target/scala-2.10/simple-project_2.10-1.0.jar"))
    val data = sc.textFile(filename, 2).cache()
    println("Number of partitions in the resilient distributed dataset = %d".
        format(data.partitions.size))
    println("Number of lines in the file = %d".format(data.count))
    println("Number of words in the file = %d".
        format(data.flatMap(_.split(" ")).count))
    sc.getConf.getAll.foreach(println)
  }
}
