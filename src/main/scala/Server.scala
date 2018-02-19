import java.io._
import java.net._


object Server {

  private val BufferSize = 1024

  def runServer(port: Int) {

    val server = new ServerSocket(port)
    val socket = server.accept()
    val out = new PrintStream(new BufferedOutputStream(socket.getOutputStream, BufferSize))

    //TODO quit on out.checkError() ?
    while (true) {

      val msg = EventProducer.next()

      println(msg)
      out.println(msg)

      Thread.sleep(100)
    }

//    out.flush()
    socket.close()
  }

}
