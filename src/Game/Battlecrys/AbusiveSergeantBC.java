package Game.Battlecrys;

import java.util.LinkedHashSet;
import java.util.Set;

import Game.BoardState;
import Game.BufferType;
import Game.ChoiceState;
import Game.MyTurnState;
import Game.PlayableCard;
import Game.Actions.ChoiceAction;
import Game.Buffs.AdditiveBuff;
import Game.Minions.Minion;
import Search.Action;

public class AbusiveSergeantBC extends Battlecry {

	@Override
	public MyTurnState perform(PlayableCard minion, BoardState oldstate) {
		System.out.println("triggering");
		Set<Action> actions = new LinkedHashSet<Action>();
		for (int id : oldstate.getIdsInPlayOrder()) {
			if (id!=((Minion) minion).getId()) {
				Minion targetMinion = oldstate.findMinion(id);
			
				if (targetMinion.isTargettable()) actions.add(new AbusiveSergeantChoice(id));
			}
		}
		System.out.println(actions.size());
		if (actions.size()==0) return oldstate;
		System.out.println("ChoiceId"+((Minion) minion).getId());
		return new ChoiceState(oldstate,actions,BufferType.BATTLECRY,((Minion) minion).getId());
	}
	
	
	public class AbusiveSergeantChoice extends ChoiceAction {
		
		private int id;
		
		public AbusiveSergeantChoice(int id) {
			this.id = id;
		}

		public MyTurnState result(BoardState oldstate) {
			return oldstate.applyTempBuff(id,new AdditiveBuff(-1,2,0,0));
		}

		@Override
		public void print() {
			System.out.println("Abusive Sergeant buffs minion");
			
		}
		
		@Override
		public String output() {
			return "Abusive Sergeant buffs minion";
			
		}

	}

}
