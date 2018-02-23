import java.io._
import java.net._
import com.opencsv.CSVWriter

object Server {

  def runServer(port: Int, amount: Int) {

    val server = new ServerSocket(port)

    println("Creating a socket...")

    val socket = server.accept()

    val csvWriter = new CSVWriter(
      new OutputStreamWriter(socket.getOutputStream),
      CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
      CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)

    println("Transmission started")

    //TODO quit on out.checkError() ?
    for(i <- 1 to amount) {
      val msg = EventProducer.next()

      csvWriter.writeNext(msg)
    }

    println("Transmission finished")

    csvWriter.close()
    socket.close()
  }

}
