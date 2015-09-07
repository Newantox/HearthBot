package Search;

import java.util.Set;

import Game.TargetsType;
import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Minions.Minion;
import Game.Weapons.Weapon;

public interface State {
	
	Set<Action> getApplicableActions();
	State getActionResult(Action action);

	State damageRandomHittable(TargetsType targets, int amount);

	State drawCard();
	
	double getValue(Node n);
	double getBestValue(Node node);
	State changeWeaponDurability(Hero target, int amount);
	State placeMinion(Minion minion);
	State performDR(MinionDeathrattle deathrattle, Minion minion);
	State performDR(WeaponDeathrattle deathrattle);
	State performBC(MinionBattlecry battlecry, Minion minion);
	State performBC(WeaponBattlecry battlecry);
	State equipHeroWeapon(Weapon weapon);
	State equipEnemyWeapon(Weapon weapon);
	
}
