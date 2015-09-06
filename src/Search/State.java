package Search;

import java.util.Set;

import Game.TargetsType;
import Game.Battlecrys.Battlecry;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Minions.Minion;

public interface State {
	
	Set<Action> getApplicableActions();
	State getActionResult(Action action);

	State damageRandomHittable(TargetsType targets, int amount);

	State drawCard();
	
	double getValue(Node n);
	double getBestValue(Node node);
	State changeWeaponDurability(Hero target, int amount);
	State performDR(Deathrattle deathrattle, Minion minion);
	State performBC(Battlecry battlecry, Minion minion);
	
}
