
import java.time.LocalDateTime


class Event {

  var productPrice: Double = _
  var productName: String = _
  var purchaseDateTime: LocalDateTime = _
  var productCategory: String = _
  var ipAddress: String = _


  override def toString = s"Event($productCategory, $productName, $productPrice, $purchaseDateTime, $ipAddress)"
}
