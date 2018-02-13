
import scala.util.Random

/*
product name uniform
product price  gaussian
purchase date   time - gaussian, date - uniform  1 week range
product category  uniform
client IP address  uniform IPv4 random adresses
 */
object EventProducer {

  private val Rand = new Random()

  def next():Double = {

    Rand.nextGaussian()
  }
}
