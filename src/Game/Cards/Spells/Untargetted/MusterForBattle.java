package Game.Cards.Spells.Untargetted;

import Game.BoardState;
import Game.Character;
import Game.MyTurnState;
import Game.Minions.Minion;
import Game.Minions.SilverHandRecruit;
import Game.Weapons.LightsJustice;

public class MusterForBattle extends UntargettedSpell {

	public MusterForBattle() {
		super("Muster for Battle", 3);
	}

	@Override
	public MyTurnState playCard(BoardState oldstate, Character target) {
		int i = oldstate.numberOfAlliedMinions();
		MyTurnState tempstate = oldstate;
		while (i<7 && i<oldstate.numberOfAlliedMinions()+3) {
			Minion minion = new SilverHandRecruit();
			tempstate = tempstate.placeMinion(minion,i);
			i++;
		}
		return tempstate.equipHeroWeapon(new LightsJustice());
	}

}
