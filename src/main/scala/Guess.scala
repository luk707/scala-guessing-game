trait GuessInputReader {
  def readInt(): Int
}

object GuessStdinReader extends GuessInputReader {
  def readInt(): Int = scala.io.StdIn.readInt()
}

case class Guess(value: Option[Int]) {
  def ==(num: Int): Boolean = value.contains(num)
  def !=(num: Int): Boolean = !value.contains(num)
}

object Guess {
  def readln(reader: GuessInputReader = GuessStdinReader): Guess = {
    try {
      Guess(Some(reader.readInt()))
    } catch {
      case _: NumberFormatException => Guess(None)
    }
  }
}
