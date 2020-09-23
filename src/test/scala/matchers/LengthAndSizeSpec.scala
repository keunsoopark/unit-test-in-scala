package matchers

import java.util.UUID

import com.h2.services.CustomerService

class LengthAndSizeSpec extends UnitSpec {

  val customerService: CustomerService = new CustomerService {}
  behavior of "CustomerService for length"

  it should "return correct length for customer's first and last name" in {
    val (first, last, email, dateOfBirth) = ("taylor", "park", "taylor.park@gmail.com", "1923/12/11")

    val customerId = customerService.createNewCustomer(first, last, email, dateOfBirth)
    val customer = customerService.getCustomer(customerId).get

    customer.first should have length first.length
    customer.last should have length last.length
  }

  behavior of "CustomerService for size"

  it should "return correct size for number of customers created" in {
    val newCustomers: Seq[(String, String, String, String)] = List(
      ("taylor", "park", "taylor.park@gmail.com", "1923/12/11"),
      ("taylor1", "park1", "taylor1.park1@gmail.com", "2123/12/11")
    )

    val customerIds: Seq[UUID] = newCustomers.map{ newCustomer =>
      customerService.createNewCustomer(newCustomer._1, newCustomer._2, newCustomer._3, newCustomer._4)
    }

    customerIds should have length 2
  }
}
