package com.scala.dsl

object TradeDSL {
	abstract class Instrument(name: String) { def instrumentName: String }
	case class Stock(name: String) extends Instrument(name) {
	  override val instrumentName = "EQUITY";
	}
	case class Bond(name: String) extends Instrument(name) {
	  override val instrumentName = "BOND";
	}
	
	abstract class TransactionType { def value: String}
	case class Buy() extends TransactionType {
	  override val value = "BUY"
	}
	
	case class Sell() extends TransactionType {
	  override val value = "SELL"
	}
	
	/*class MeaningfulInt(quantity: Int){
	  def sharesOf(stock: Stock){
	    (quantity,stock)
	  }
	  
	  def bondsOf(bond: Bond ) {
	    (quantity,bond)
	  }
	}
	
	implicit def orderInt(i: Int) = new MeaningfulInt(i)*/
	
	case class Order(price: Int = 0, instrument: Instrument = null, quantity: Int = 0, totalValue: Int = 0,trn: TransactionType = null, account: String = null ) {
	  def maxUnitPrice(p: Int) = copy(price = p)
	  def to(i: Tuple2[Instrument, Int] ) = copy(instrument = i._1, quantity = i._2)
	  def buy(qi: Tuple2[Int, Instrument]) = copy(instrument = qi._2, quantity = qi._1, trn = Buy())
	  def sell(qi: Tuple2[Int, Instrument]) = copy(instrument = qi._2, quantity = qi._1, trn = Sell())
	  def using(pricing: (Int, Int) => Int) = {
		  copy(totalValue = pricing(quantity, price))
	  }
	  def forAccount(a: String)(implicit pricing: (Int,Int) => Int) = {
	    copy(account = a, totalValue = pricing(quantity,price))
	  }
	  
    }
	
	  
	def main(args: Array[String]) = {
	  val stock = Stock("GOOGLE");
	  val bond = Bond("IBM");
	  def defaultPricing(qty: Int, price: Int): Int = qty * price
	  def brokeragePricing(qty: Int,price: Int): Int = (qty * price * 1) + qty * price
	  
	  val order1 = new Order()
	  .buy(10, Stock("GOLD"))
	  .maxUnitPrice(25)
	  .using(brokeragePricing)
	  
	  val order2 = new Order()
	  .buy(10, Stock("GOLD"))
	  .maxUnitPrice(25)
	  .using(defaultPricing)
	  
	  val order3 = new Order()
	  .sell(10, Stock("GOLD"))
	  .maxUnitPrice(25)
	  .using( (qty,price) => ((qty + 100) * price))
	  
	  println(order1)
	  println(order2)
	  println(order3)
	  
	}
}