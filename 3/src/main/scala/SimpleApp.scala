// Copied from http://apache-spark-user-list.1001560.n3.nabble.com/streaming-code-to-simulate-a-network-socket-data-source-td3431.html
import java.net.ServerSocket
import java.io.PrintWriter
import scala.io.Source

object StreamingDataGenerator {

  def main(args : Array[String]) {
    if (args.length != 3) {
      System.err.println("Usage: StreamingDataGenerator <port> <file> <bytesPerSecond>")
      System.exit(1)
    }
    val port = args(0).toInt
    val file = Source.fromFile(args(1))
    val bytesPerSecond = args(2).toFloat
    
    val sleepDelayMs = (1000.0 / bytesPerSecond).toInt
    val listener = new ServerSocket(port)
    
    println("Reading from file: " + file.descr)

    while (true) {
      println("Listening on port: " + port)
      val socket = listener.accept()
      new Thread() {
        override def run = {
          println("Got client connect from: " + socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream(), true)

          file.foreach(c => 
            {
              Thread.sleep(sleepDelayMs)
              // write the byte to the socket
              out.write(c)
              out.flush()
              // also print the byte to stdout, for debugging ease
              print(c)
            }
          )
          socket.close()
        }
      }.start()
    }
  }
}
