package akka.first.app.scala

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.first.app.scala.actors.MasterActor


object PrimeNumbersApplication {

  def main(args: Array[String]) {
    val maxNumbers = 1000
    val system = ActorSystem("PrimeNumbers")

    val masterActor = system.actorOf(Props(new MasterActor(maxNumbers)), name = "Prime-numbers")

    for (x <- 1 to maxNumbers) {
      masterActor ! x
    }


    Thread.sleep(2500)
    system.shutdown

  }
}