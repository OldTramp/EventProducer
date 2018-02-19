import java.io._
import java.net._
import com.opencsv.CSVWriter

object Server {

  private val BufferSize = 1024

  def runServer(port: Int) {

    val server = new ServerSocket(port)
    val socket = server.accept()

    val csvWriter = new CSVWriter(
      new OutputStreamWriter(socket.getOutputStream),
      CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
      CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)

    //TODO quit on out.checkError() ?
    while (true) {
      val msg = EventProducer.next()

      csvWriter.writeNext(msg)
      //println(msg)

      Thread.sleep(100)
    }

    csvWriter.close()
    socket.close()
  }

}
