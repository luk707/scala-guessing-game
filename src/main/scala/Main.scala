import scala.util.Random

@main def hello(): Unit = {
  println("Guess a number between 1 and 100!")

  val rand = new Random
  val correctNumber = rand.nextInt(100) + 1
  var guess = Guess(None)

  while (guess != correctNumber) {
    guess = Guess.readln()
    guess match {
      case Guess(Some(value)) => {
        if (value < correctNumber) {
          println("Higher!")
        }
        if (value > correctNumber) {
          println("Lower!")
        }
        if (value == correctNumber) {
          println("You got it!")
        }
      }
      case Guess(None) => println("I don't understand your guess")
    }
  }
}
