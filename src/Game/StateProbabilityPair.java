package Game;

public class StateProbabilityPair {

  private final MyTurnState state;
  private final double probability;
  private final String text;

  public StateProbabilityPair(MyTurnState state, double probability, String text) {
    this.state = state;
    this.probability = probability;
    this.text = text;
  }

  public MyTurnState getState() { return state; }
  
  public double getProbability() { return probability; }
  
  public String getText() { return text; }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof StateProbabilityPair)) return false;
    StateProbabilityPair other = (StateProbabilityPair) that;
    return this.state.equals(other.getState()) &&
           this.probability == (other.getProbability());
  }
  
  @Override
  public int hashCode() { 
	  return state.hashCode(); 
  }
  
  public void print() {
	  System.out.println("Resolved: "+text);
  }

}
