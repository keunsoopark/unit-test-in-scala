package matchers

import com.h2.services.Currency

class ContainerSpec extends UnitSpec {

  behavior of "Currencies in a wallet"

  it should "contain a currency that is added to a List wallet" in {
    val oneUsd: Currency = "1 USD"
    val twoEur: Currency = "2 EUR"
    val tenCad: Currency = "10 CAD"

    val wallet = List(oneUsd, twoEur, tenCad)

    wallet should contain (oneUsd)
  }

  it should "contain a currency that is added to a Set wallet" in {
    val oneUsd: Currency = "1 USD"
    val twoEur: Currency = "2 EUR"
    val tenCad: Currency = "10 CAD"

    val wallet = Set(oneUsd, twoEur, tenCad)

    wallet should contain (oneUsd)
  }

  it should "contain a currency that is added to a Map wallet" in {
    val oneUsd: Currency = "1 USD"
    val twoEur: Currency = "2 EUR"
    val tenCad: Currency = "10 CAD"

    val wallet = Map(
      "USD" -> oneUsd,
      "EUR" -> twoEur,
      "CAD" -> tenCad
    )

    wallet should contain ("USD" -> oneUsd)
  }

  it should "contain a OneOf 1 USD that is added to Set wallet" in {
    val oneUsd: Currency = "1 USD"
    val twoEur: Currency = "2 EUR"
    val tenCad: Currency = "10 CAD"
    val hundredInr: Currency = "10 INR"

    val wallet = Set(oneUsd, oneUsd, twoEur, tenCad)

    wallet should contain oneOf (oneUsd, hundredInr)
//    wallet should contain oneOf (oneUsd, twoEur)  // <- fail as both are in "wallet"

    // use "oneElementOf" instead of "oneOf" for List
    wallet should contain oneElementOf List(oneUsd, hundredInr)
  }

  it should "contain a NoneOf 100 INR that is added to Set wallet" in {
    val oneUsd: Currency = "1 USD"
    val twoEur: Currency = "2 EUR"
    val tenCad: Currency = "10 CAD"
    val hundredInr: Currency = "10 INR"

    val wallet = Set(oneUsd, oneUsd, twoEur, twoEur)

    wallet should contain noneOf (hundredInr, tenCad)
    wallet should contain noElementsOf List(hundredInr, tenCad)
  }
}
