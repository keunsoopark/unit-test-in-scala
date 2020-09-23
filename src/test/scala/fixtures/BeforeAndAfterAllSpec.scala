package fixtures

import com.h2.entities.Customer
import com.h2.services.CustomerService
import matchers.UnitSpec
import org.scalatest.BeforeAndAfterAll

// Unlike BeforeAndAfter, Here beforeAll and afterAll methods are executed when all the test is over.
class BeforeAndAfterAllSpec extends UnitSpec with BeforeAndAfterAll {

  var customers: List[Customer] = List.empty

  override protected def beforeAll(): Unit = {
    val service = new CustomerService {}
    val johnId = service.createNewCustomer("John", "Doe", "doe@google.com", "1985/02/12")
    val amyId = service.createNewCustomer("Amy", "Williams", "amy@google.com", "1982/12/10")

    customers = List(johnId, amyId).map(id => service.getCustomer(id).get)
    info("BeforeAndAfterAllSpec: Customers Created, Starting Tests")
  }

  override protected def afterAll(): Unit = {
    info("BeforeAndAfterAllSpec: All Tests Complete. Cleaning up memory")
    customers = List.empty
  }

  // no need "behavior of xx"
  it should "report 2 exisiting customers available" in {
    customers should have size 2
  }

  it should "correctly match that all customer have small from 'google'" in {
    customers.map { customer =>
      customer.email.toString should include("google")
    }
  }
}
