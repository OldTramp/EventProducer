
import scala.util.Random
import com.opencsv.CSVReader
import java.io.{InputStream, InputStreamReader}
import java.time.{LocalDate, LocalDateTime, LocalTime}
import java.util

import net.andreinc.mockneat.MockNeat
import net.andreinc.mockneat.types.enums.IPv4Type

/*
product name uniform
product category  uniform
product price  gaussian
purchase date   time - gaussian, date - uniform  1 week range
client IP address  uniform IPv4 random adresses
 */
object EventProducer {

  private val SecInDay = 60 * 60 * 24

  val stream: InputStream = getClass.getResourceAsStream("/products.csv")
  private val csvReader = new CSVReader(new InputStreamReader(stream))
  private val Products: util.List[Array[String]] = csvReader.readAll()

  private val Rand = new Random()
  private val MNeat: MockNeat = MockNeat.threadLocal

  def next(): Array[String] = {
    val product = Products.get(Rand.nextInt(Products.size()))
    val productCategory: String = product(0)
    val productName: String = product(1)

    val productPrice: Double = gaussianPrice

    val ipAddress: String = MNeat.ipv4s().types(IPv4Type.CLASS_A, IPv4Type.CLASS_B, IPv4Type.CLASS_C).`val`()

    val purchaseDateTime: LocalDateTime = MNeat.localDates()
      .between(LocalDate.of(2018, 2, 12), LocalDate.of(2018, 2, 19)).`val`()
      .atTime(gaussianTime)

    val event: Array[String] = Array(productCategory, productName, productPrice.toString, purchaseDateTime.toString, ipAddress)
    event
  }

  def gaussianTime: LocalTime = {

    var second = Rand.nextGaussian * SecInDay / 5 + SecInDay / 2

    if (second > SecInDay) {
      second = SecInDay - 1
    } else if (second <= 0) {
      second = 1
    }

    LocalTime.ofSecondOfDay(second.toLong)
  }

  def gaussianPrice: Double = {

    val scaled = Math.abs((Rand.nextGaussian + 10) * 100)

    (scaled * 100).round / 100.0
  }

}
