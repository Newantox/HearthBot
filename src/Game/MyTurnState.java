package Game;

import Game.Battlecrys.MinionBattlecry;
import Game.Battlecrys.WeaponBattlecry;
import Game.Deathrattles.MinionDeathrattle;
import Game.Deathrattles.WeaponDeathrattle;
import Game.Heroes.Hero;
import Game.Inspires.MinionInspire;
import Game.Inspires.WeaponInspire;
import Game.Minions.Minion;
import Game.Weapons.Weapon;
import Search.State;

public interface MyTurnState extends State {
	
	MyTurnState resolveRNG();
	
	boolean isTurnEnded();
	void setTurnEnded(boolean b);
	
	MyTurnState damageRandomHittable(TargetsType targets, int amount);
	
	MyTurnState drawCard();
	MyTurnState enemyDrawCard();
	
	MyTurnState placeMinion(Minion minion);
	MyTurnState performBC(MinionBattlecry battlecry, Minion minion);
	MyTurnState performBC(WeaponBattlecry battlecry);
	MyTurnState performDR(MinionDeathrattle deathrattle, Minion minion);
	MyTurnState performDR(WeaponDeathrattle deathrattle);
	MyTurnState performInspire(MinionInspire inspire, Minion minion);
	MyTurnState performInspire(WeaponInspire inspire);
	MyTurnState changeHeroWeaponAtkDurability(int amountAtk, int amountDurability);
	MyTurnState changeEnemyWeaponAtkDurability(int amountAtk, int amountDurability);
	MyTurnState giveWeapon(Hero hero, Weapon weapon);
	MyTurnState equipHeroWeapon(Weapon weapon);
	MyTurnState equipEnemyWeapon(Weapon weapon);
	MyTurnState applyAuras(Minion minion);
	MyTurnState removeAuras(Minion minion);
	MyTurnState doSummonEffects(Minion minion);
	MyTurnState doDeathEffects(Minion minion);
	MyTurnState changeAtkHP(Minion minion, int amountAtk, int amountHP);
	MyTurnState changeAttributes(Minion minion, boolean charge, boolean divineshield, boolean taunt, boolean stealth, boolean windfury, int spelldamage, boolean frozen);
	
	MyTurnState doStartTurnEffects(Hero hero);
	MyTurnState doEndTurnEffects(Hero hero);

	MyTurnState viewBiased();

	MyTurnState drawCard(int pos);
	
	

}
