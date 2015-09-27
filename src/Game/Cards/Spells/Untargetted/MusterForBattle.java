package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;
import Game.Weapons.LightsJustice;

public class MusterForBattle extends UntargettedSpell {

	public MusterForBattle() {
		super("Muster for Battle", 3);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, int target) {
		int i = oldstate.numberOfAlliedMinions();
		MyTurnState tempstate = oldstate;
		while (i<7 && i<oldstate.numberOfAlliedMinions()+3) {
			Minion minion = new SilverHandRecruit(i);
			tempstate = tempstate.placeMinion(minion);
		}
		return tempstate.equipHeroWeapon(new LightsJustice());
	}

}
