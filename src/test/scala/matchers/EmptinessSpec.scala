package matchers

import com.h2.services.{Currency, CustomerService}

class EmptinessSpec extends UnitSpec {

  val customerService: CustomerService = new CustomerService {}
  behavior of "Customer for emptiness"

  it should "return empty for customer's last name" in {
    val (first, last, email, dateOfBirth) = ("taylor", "", "taylor@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.last should be (empty)
  }

  behavior of "Currencies inside wallet"
  it should "return empty when no currencies are added in wallet" in {
    val wallet: List[Currency] = List.empty

    wallet should be (empty)
  }
}
