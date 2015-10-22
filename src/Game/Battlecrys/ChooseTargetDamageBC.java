package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.Character;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Search.Action;

public class ChooseTargetDamageBC extends Battlecry {
	
	private int amount;
	
	public ChooseTargetDamageBC(int amount) {
		this.amount = amount;
	}
	
	@Override
	public MyTurnState perform(PlayableCard card, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int position : oldstate.getPositionsInPlayOrder()) {
			if (position<7) {
				if (((oldstate.getMySide()).get(position)).isTargettable()) actions.add(new TargetChoice(oldstate.getMySide().get(position)));
			}
			
			else if (((oldstate.getOppSide()).get(position-7)).isTargettable()) actions.add(new TargetChoice(oldstate.getOppSide().get(position-7)));
		}
		actions.add(new TargetChoice(oldstate.getHero()));
		actions.add(new TargetChoice(oldstate.getEnemy()));
		
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class TargetChoice extends ChoiceAction {
		
		private Character target;
		
		public TargetChoice(Character target) {
			this.target = target;
		}
		
		@Override
		public MyTurnState result(BoardState oldstate) {
			return oldstate.damage(target,amount);
		}

		@Override
		public void print() {
			System.out.println("Damage minion at "+target);
			
		}
		
	}
}
