package matchers

import com.h2.services.CustomerService

class StringSpec extends UnitSpec {

  val customerService: CustomerService = new CustomerService {}
  behavior of "Customer Service for String"

  it should "correctly match the customer email starting with the first name" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should startWith(first.toLowerCase())
  }

  it should "correctly match the customer email ending with the '.com'" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should endWith(".com")
  }

  it should "correctly match the customer email containing the '@'" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should include("@")
  }

  it should "correctly match the customer email as regular expression" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.email.toString should include regex "[a-z]+[@.]com"
  }

  it should "correctly match the date of birth as fully matches regular expression" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.dateOfBirth.toString should fullyMatch regex "[0-9]{4}-[0-9]{2}-[0-9]{2}"
  }
}
