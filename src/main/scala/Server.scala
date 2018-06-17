import java.io._
import java.net._
import com.opencsv.CSVWriter

object Server {

  def runServer(port: Int, amount: Int) {

    println("Connecting to the port " + port)

    val socket = new Socket("127.0.0.1", port)

    val csvWriter = new CSVWriter(
      new OutputStreamWriter(socket.getOutputStream),
      CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
      CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)

    println("Transmission started")

    for(i <- 1 to amount) {
      val msg = EventProducer.next()

      csvWriter.writeNext(msg)
    }

    println("Transmission finished")

    csvWriter.close()
    socket.close()
  }

}
