case class GuessMockReader(reader: () => Int) extends GuessInputReader {
  def readInt(): Int = reader()
}

class GuessSuite extends munit.FunSuite {
  test("Guess == Int - wrong number") {
    val guess = Guess(Some(45))
    val correctNumber = 30
    val expected = false
    val result = guess == correctNumber
    assertEquals(expected, result)
  }

  test("Guess != Int - wrong number") {
    val guess = Guess(Some(45))
    val correctNumber = 30
    val expected = true
    val result = guess != correctNumber
    assertEquals(expected, result)
  }

  test("Guess == Int - correct number") {
    val guess = Guess(Some(45))
    val correctNumber = 45
    val expected = true
    val result = guess == correctNumber
    assertEquals(expected, result)
  }

  test("Guess != Int - correct number") {
    val guess = Guess(Some(45))
    val correctNumber = 45
    val expected = false
    val result = guess != correctNumber
    assertEquals(expected, result)
  }

  test("Guess == Int no guess") {
    val guess = Guess(None)
    val correctNumber = 45
    val expected = false
    val result = guess == correctNumber
    assertEquals(expected, result)
  }

  test("Guess != Int no guess") {
    val guess = Guess(None)
    val correctNumber = 45
    val expected = true
    val result = guess != correctNumber
    assertEquals(expected, result)
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
