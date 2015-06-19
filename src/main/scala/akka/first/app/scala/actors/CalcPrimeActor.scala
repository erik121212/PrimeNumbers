package akka.first.app.scala.actors

import akka.actor.{Actor, ActorRef}


class CalcPrimeActor(callingActor: ActorRef) extends Actor {

  def receive: Receive = {
    case message: Int =>
      callingActor ! IsPrimeResult(message, isPrime(message))
  }

  def isPrime(i: Integer): Boolean = {
    def isPrimeIterator(i: Int, prime: Int): Boolean = {
      if (i == 1) true
      else {
        if (prime % i == 0) false else isPrimeIterator(i - 1, prime)
      }
    }

    if (i <= 1) false
    else if (i < 1) false else isPrimeIterator(i - 1, i)
  }


}
