
object Main extends App {

  var port = 10000
  var amount = 3000

  args.sliding(2, 2).toList.collect {
    case Array("--port", argPort: String) => port = argPort.toInt
    case Array("--amount", argAmount: String) => amount = argAmount.toInt  }

  Server.runServer(port = port, amount = amount)

}