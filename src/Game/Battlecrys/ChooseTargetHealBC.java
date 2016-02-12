package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.Character;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Minions.Minion;
import Search.Action;

public class ChooseTargetHealBC extends Battlecry {
	
	private int amount;
	
	public ChooseTargetHealBC(int amount) {
		this.amount = amount;
	}
	
	@Override
	public MyTurnState perform(PlayableCard card, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int id : oldstate.getIdsInPlayOrder()) {
			Minion targetMinion = oldstate.findMinion(id,"");
			
			if (targetMinion.isTargettable()) actions.add(new TargetChoice(targetMinion));
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
			return oldstate.heal(target,amount);
		}

		@Override
		public void print() {
			System.out.println("Heal "+target.getName());
			
		}
		
		@Override
		public String output() {
			return ("Abusive Sergeant buffs minion");
			
		}
		
	}
}
