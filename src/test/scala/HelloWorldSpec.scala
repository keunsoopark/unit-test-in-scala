import org.scalatest.flatspec.AnyFlatSpec

class HelloWorldSpec extends AnyFlatSpec {
  behavior of "Hello World"

  it should "start with 'Hello'" in {
    assert("HelloWorld".startsWith("Hello"))
  }
  it should "ends with 'World'" in {
    assert("HelloWorld".endsWith("World"))
  }
}