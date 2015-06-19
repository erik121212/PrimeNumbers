package akka.first.app.scala.actors

import akka.actor.{Actor, ActorRef, Props}

class MasterActor(totalIncoming: Int) extends Actor {
  private val totalIncomingMsgs = totalIncoming
  private var incomingMessages = 0
  private var messagesProcessed = 0


  // See http://www.tutorialspoint.com/scala/scala_maps.htm
  val numberOfActors = 10
  var actorMap:Map[Int, ActorRef] = Map()
  for (x <- 0 to numberOfActors-1) {
    actorMap += (x -> context.actorOf(Props(new CalcPrimeActor(self))))
  }


  private val start = System.currentTimeMillis()

  def receive: Receive = {
    case message: Int   =>
      incomingMessages += 1
      actorMap(incomingMessages % numberOfActors) ! message

    case message: IsPrimeResult =>
      messagesProcessed += 1
      if (totalIncomingMsgs == messagesProcessed) {
        println("Done, it took " + (System.currentTimeMillis() - start) + " milliseconds to process " + messagesProcessed + " messages")
      }
      if (message.isPrime)
       println("IsPrime: " + message.primeNumber + " = " + message.isPrime)
  }




}
