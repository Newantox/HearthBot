package Game;

import java.util.ArrayList;
import java.util.List;

import GUI.StateString;
import Search.Action;
import Search.State;

public class GamePlay {
	
	private int winner;
	
	List<ArrayList<StateString>> list;
	List<Integer> stepsInTurn;
	int totalSteps;
	int totalTurns;
	
	StateString currentStateString;
	int currentStep;
	int currentStepInTurn;
	int currentTurn;
	
	
	
	public GamePlay() {
		list = new ArrayList<ArrayList<StateString>>();
		stepsInTurn = new ArrayList<Integer>();
		totalSteps = 0;
		totalTurns = 0;
		
		currentStateString = null;
		currentStep = 0;
		currentStepInTurn = 0;
		currentTurn = 0;
		
		
	}
	
	public String outputBoard() {
		return (currentStateString.getState()).output();
	}
	
	public String outputText() {
		return currentStateString.getText();
	}
	
	public int getWinner() {
		return winner;
	}
	
	public void reset() {
		currentStateString = (list.get(0)).get(0);
		currentStep = 0;
		currentStepInTurn = 0;
		currentTurn = 0;
	}
	
	public void setWinner(int w) {
		this.winner = w;
	}
	
		public void prevStep() {
			if (currentStepInTurn>0) {
				currentStep--;
				currentStepInTurn--;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
			else if (currentTurn>0) {
				currentStep--;
				currentTurn--;
				currentStepInTurn = stepsInTurn.get(currentTurn)-1;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
		}
		
		public void nextStep() {
			if (currentStepInTurn<stepsInTurn.get(currentTurn)-1) {
				currentStep++;
				currentStepInTurn++;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
			else if (currentTurn<totalTurns-1) {
				currentStep++;
				currentTurn++;
				currentStepInTurn = 0;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
		}
		
		public void prevTurn() {
			if (currentTurn>0) {
				currentStep = currentStep - currentStepInTurn - stepsInTurn.get(currentTurn);
				currentStepInTurn = 0;
				currentTurn--;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
			System.out.println(currentTurn);
		}
		
		public void nextTurn() {
			if (currentTurn<totalTurns-1) {
				currentStep = currentStep + stepsInTurn.get(currentTurn) - currentStepInTurn;
				currentStepInTurn = 0;
				currentTurn++;
				currentStateString = (list.get(currentTurn)).get(currentStepInTurn);
			}
		}
		
		public void addNewStep(State state, Action action) {
			StateString newStateString;
			if (action!=null) newStateString = new StateString(state,action.output());
			else newStateString = new StateString(state,"Start of Turn");
			if (currentStateString==null) currentStateString = newStateString;
			ArrayList<StateString> templist = list.get(totalTurns-1);
			templist.add(newStateString);
			list.set(totalTurns-1, templist);
			stepsInTurn.set(totalTurns-1, stepsInTurn.get(totalTurns-1)+1);
			totalSteps++;
		}
		
		public void addNewTurn(State state) {
			StateString newStateString = new StateString(state,"Start of Turn");
			if (currentStateString==null) currentStateString = newStateString;
			ArrayList<StateString> templist = new ArrayList<StateString>();
			templist.add(newStateString);
			list.add(templist);
			stepsInTurn.add(1);
			totalSteps++;
			totalTurns++;
		}
}
