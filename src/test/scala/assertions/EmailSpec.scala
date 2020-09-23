package assertions

import com.h2.entities.Email
import org.scalatest.flatspec.AnyFlatSpec

class EmailSpec extends AnyFlatSpec {

  behavior of "An Email"

  ignore should "not implemented test yet" in {
    assert(1 === 0)
  }

  it should "return an Email object for a valid String" in {
    val email = Email("whwr0428@gmail.com")

    assert(email.localPart === "whwr0428", "expected localPart is 'whwr0428'")
    assert(email.domain === "gmail.com")
  }

  it should "return another Email object for another valid String" in {
    assertResult("jim", "expected localPart is 'jim'") {
      Email("jim@zcvj.com").localPart
    }
  }

  it should "throw an exception when an email does not contain @ symbol" in {
    withClue("expected exception since email address does not contain @") {
      assertThrows[IllegalArgumentException] {
        Email("kjczv.google.com")
      }
    }
  }

  it should "throw an exception when an email contain more than one @ symbol" in {
    assertThrows[IllegalArgumentException] {
      Email("kjczv@google@com")
    }
  }

  it should "intercept the correct error message when no '@' symbol is provided" in {
    val exception = intercept[IllegalArgumentException] { Email("clvkjl.com") }
    assert(exception.isInstanceOf[IllegalArgumentException])
    assert(exception.getMessage.contains("does not contain '@' symbol"))
  }

  it should "intercept the correct error message when more than on '@' symbol is provided" in {
    val exception = intercept[IllegalArgumentException] { Email("whwr0428@gmail@com") }
    assert(exception.isInstanceOf[IllegalArgumentException])
    assert(exception.getMessage.contains("should not contain '@' symbol more than once"))
  }
}
