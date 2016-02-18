package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.BufferType;
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
			Minion targetMinion = oldstate.findMinion(id);
			
			if (targetMinion.isTargettable()) actions.add(new TargetChoice(id));
		}
		if (actions.size()==0) return oldstate;
		return new ChoiceState(oldstate,actions,BufferType.BATTLECRY,((Minion) card).getId());
	}
	
	
	public class TargetChoice extends ChoiceAction {
		
		private int id;
		
		public TargetChoice(int id) {
			this.id = id;
		}
		
		@Override
		public MyTurnState result(BoardState oldstate) {
			return oldstate.applyBuff(id,buff);
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
