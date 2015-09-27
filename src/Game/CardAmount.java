package Game;

public class CardAmount {

	  private final Card card;
	  private int amount;

	  public CardAmount(Card card, int amount) {
	    this.card = card;
	    this.amount = amount;
	  }

	  public Card getCard() { 
		  return card; 
	  }
	  
	  public void setAmount(int amount) {
		  this.amount = amount;
	  }
	  
	  public int getAmount() {
		  return amount;
	  }

	  @Override
	  public boolean equals(Object that) {
	    if (!(that instanceof CardAmount)) return false;
	    CardAmount other = (CardAmount) that;
	    return this.card.equals(other.getCard()) &&
	           this.amount == (other.getAmount());
	  }
	  
	  @Override
	  public int hashCode() { 
		  return card.hashCode(); 
	  }

}
