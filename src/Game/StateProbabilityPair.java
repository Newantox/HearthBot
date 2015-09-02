package Game;

import Search.State;

public class StateProbabilityPair {

  private final State state;
  private final double probability;

  public StateProbabilityPair(State state, double probability) {
    this.state = state;
    this.probability = probability;
  }

  public State getState() { 
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
