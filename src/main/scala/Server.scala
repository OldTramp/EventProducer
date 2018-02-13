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
      Thread.sleep(100)

      println(msg)
      out.println(msg)

    }

//    out.flush()
    socket.close()
  }

}
