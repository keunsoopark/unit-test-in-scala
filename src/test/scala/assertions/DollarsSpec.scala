package assertions

import com.h2.entities.Dollars
import org.scalatest.flatspec.AnyFlatSpec

class DollarsSpec extends AnyFlatSpec {

  behavior of "A Dollar"

  it should "create a correct Dollar object for number 10 as an input" in {
    val tenDollars = Dollars(10)

    assert("$10" === tenDollars.toString)
  }

  it should "correctly identify that $10 > $5" in {
    val tenDollars = Dollars(10)
    val fiveDollars = Dollars(5)

//    assert(tenDollars.compare(fiveDollars) === 5)
    assert(tenDollars > fiveDollars)
  }

  it should "correctly add two Dollars amounts" in {
    val tenDollars = Dollars(10)
    val twoDollars = Dollars(2)

    assertResult("$12") {
      (tenDollars + twoDollars).toString
    }
  }

  it should "correctly substract two Dollars amounts" in {
    val tenDollars = Dollars(10)
    val twoDollars = Dollars(2)

    assertResult("$8") {
      (tenDollars - twoDollars).toString
    }
  }

  it should "correctly identify $4 to $4" in {
    val fourDollars = Dollars(4)

    assertResult(true) {
      fourDollars === fourDollars
    }
  }

  it should "throw an exception when an invalid integer is provided to create Dollars" in {
    assertThrows[ArithmeticException]{
      Dollars(10 / 0)
    }
  }

  it should "have every dollar more than 0" in {
    // assume API call
//    val dollars: List[Dollars] = List(Dollars(1), Dollars(10), Dollars(100))
    val dollars: List[Dollars] = List.empty

    // This test is run only when "dollars" is not empty.
    assume(dollars.nonEmpty, "-- Clue: The dollars coming from API should not be empty")

    dollars.foreach { d =>
      assert(d.amount > 0)
    }
  }
}
