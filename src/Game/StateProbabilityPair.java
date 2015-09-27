package Game;

public class StateProbabilityPair {

  private final MyTurnState state;
  private final double probability;

  public StateProbabilityPair(MyTurnState state, double probability) {
    this.state = state;
    this.probability = probability;
  }

  public MyTurnState getState() { 
	  return state; 
  }
  
  public double getProbability() { return probability; }

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

}
