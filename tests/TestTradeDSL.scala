import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.scala.dsl._
import com.scala.dsl.TradeDSL.Bond
import com.scala.dsl.TradeDSL.Stock
import com.scala.dsl.TradeDSL.Buy
import com.scala.dsl.TradeDSL.Sell

@RunWith(classOf[JUnitRunner])
class TestTradeDSL extends FunSuite {
  test("check instrument types: equity, bond") {
    assert(Bond("IBM").name == "IBM")
    assert(Stock("GOOGLE").name == "GOOGLE")
  }
  
  test("check transaction types: buy,sell") {
	  assert(Buy().value == "BUY" )
	  assert(Sell().value == "SELL" )
  }
  
  test("Meaningful Int") {
    //assert(100 sharesOf(Bond("IBM")))
  }
  
}