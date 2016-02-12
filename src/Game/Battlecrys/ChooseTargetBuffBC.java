package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Buffs.Buff;
import Game.Minions.Minion;
import Search.Action;

public class ChooseTargetBuffBC extends Battlecry {
	
	private Buff buff;
	
	public ChooseTargetBuffBC(Buff buff) {
		this.buff = buff;
	}
	
	@Override
	public MyTurnState perform(PlayableCard card, BoardState oldstate) {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int id : oldstate.getIdsInPlayOrder()) {
			Minion targetMinion = oldstate.findMinion(id,"");
			
			if (targetMinion.isTargettable()) actions.add(new TargetChoice(id,targetMinion.getName()));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions);
	}
	
	
	public class TargetChoice extends ChoiceAction {
		
		private int id;
		private String name;
		
		public TargetChoice(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		@Override
		public MyTurnState result(BoardState oldstate) {
			return oldstate.applyBuff(id,name,buff);
		}

		@Override
		public void print() {
			System.out.println("Buff minion");
			
		}
		
		@Override
		public String output() {
			return ("Abusive Sergeant buffs minion");
			
		}
		
	}
}
