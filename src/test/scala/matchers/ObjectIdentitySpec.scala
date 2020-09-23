package matchers

import com.h2.services.CustomerService

class ObjectIdentitySpec extends UnitSpec {

  val customerService: CustomerService = new CustomerService {}
  behavior of "CustomerService when creating new customers"

  it should "create one customer for a given email address" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    // For the same email address, .createNewCustomer() method returns the same customerId
    val customerId1 = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customerId2 = customerService.createNewCustomer(first, last, email, dateOfBirth)

    val customer1 = customerService.getCustomer(customerId1).get
    val customer2 = customerService.getCustomer(customerId2).get

    // compare two object instance
    customer1 should be theSameInstanceAs customer2
  }

}
