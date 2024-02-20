case class GuessMockReader(reader: () => Int) extends GuessInputReader {
  def readInt(): Int = reader()
}

enum EqCheck:
  case Eq, Neq

enum GuessType:
  case Correct, Incorrect, None

class GuessSuite extends munit.FunSuite {
  for {
    eqCheck <- EqCheck.values
    guessType <- GuessType.values
  } yield {
    val eqCheckTitle = eqCheck match {
      case EqCheck.Eq => "Guess == Int"
      case EqCheck.Neq => "Guess != Int"
    }

    val guessTypeTitle = guessType match {
      case GuessType.Correct => "correct number"
      case GuessType.Incorrect => "wrong number"
      case GuessType.None => "no guess"
    }

    test(s"$eqCheckTitle - $guessTypeTitle") {
      val guess = guessType match {
        case GuessType.None => Guess(None)
        case _ => Guess(Some(45))
      }
      val correctNumber = guessType match {
        case GuessType.Correct => 45
        case _ => 30
      }
      val expected = guessType match {
        case GuessType.Correct => eqCheck == EqCheck.Eq
        case _ => eqCheck == EqCheck.Neq
      }
      val result = eqCheck match {
        case EqCheck.Eq => guess == correctNumber
        case EqCheck.Neq => guess != correctNumber
      }
      assertEquals(expected, result)
    }
  }

  test("Guess.readln Correctly handles NumberFormatException") {
    val mockReader = GuessMockReader(() => {
      throw new NumberFormatException()
    })

    val guess = Guess.readln(mockReader)
    assertEquals(guess.value, None)
  }

  test("Guess.readln Correctly uses the value from the reader") {
    val mockReader = GuessMockReader(() => {
      42
    })

    val guess = Guess.readln(mockReader)
    assertEquals(guess.value, Some(42))
  }
}
