package GUI;

import Search.State;

public class StateString {

  private final State state;
  private final String text;

  public StateString(State state, String text) {
    this.state = state;
    this.text = text;
  }

  public State getState() { return state; }
 
  public String getText() { return text; }

}
