package Game;

import java.util.ArrayList;

import Game.Auras.Aura;
import Game.Battlecrys.Battlecry;
import Game.Buffs.Buff;
import Game.DeathEffects.DeathEffect;
import Game.Deathrattles.Deathrattle;
import Game.Heroes.Hero;
import Game.Inspires.Inspire;
import Game.Minions.Minion;
import Game.SummonEffects.SummonEffect;
import Game.Weapons.Weapon;
import Search.State;

public interface MyTurnState extends State {
	
	MyTurnState resolveRNG(boolean prints);
	
	boolean isTurnEnded();
	void setTurnEnded(boolean b);
	
	MyTurnState damageRandomHittable(TargetsType targets, int amount);
	
	MyTurnState drawCard();
	MyTurnState enemyDrawCard();
	
	MyTurnState placeMinion(Minion minion, int position);
	MyTurnState performBC(Battlecry battlecry, PlayableCard card);
	MyTurnState performDR(Deathrattle deathrattle, PlayableCard card, TargetsType side);
	MyTurnState performInspire(Inspire inspire, PlayableCard card);
	MyTurnState changeHeroWeaponAtkDurability(int id, int amountAtk, int amountDurability);
	MyTurnState changeEnemyWeaponAtkDurability(int id, int amountAtk, int amountDurability);
	MyTurnState giveWeapon(Hero hero, Weapon weapon);
	MyTurnState equipHeroWeapon(Weapon weapon);
	MyTurnState equipEnemyWeapon(Weapon weapon);
	MyTurnState applyAura(Aura aura, int summonedMinionId, int id);
	MyTurnState applyAuras(int minionId);
	MyTurnState removeAura(Aura aura, Minion source, int targetId);
	MyTurnState removeAuras(Minion minion);
	MyTurnState doSummonEffect(SummonEffect summonEffect, int id, int summonedMinionId, TargetsType side);
	MyTurnState doSummonEffects(int minionId);
	MyTurnState doDeathEffect(DeathEffect deathEffect, int id, Minion destroyedMinion);
	MyTurnState doDeathEffects(Minion minion, TargetsType side);
	MyTurnState applyBuff(int minionID, Buff buff);
	MyTurnState removeBuff(int minionID, int id);
	
	MyTurnState simultaneousHeal(TargetsType enemyminions, int i, ArrayList<Minion> arrayList);
	
	MyTurnState doStartTurnEffects(Hero hero);
	MyTurnState doEndTurnEffects(Hero hero);

	MyTurnState viewBiased();
	MyTurnState predictUnbiased();

	MyTurnState drawCard(int pos);

	MyTurnState damage(Character defender, int atk);

	MyTurnState heroAttack(int id, Hero defender);
	MyTurnState heroAttack(int id, Minion defender);

	MyTurnState removeTempEffects();

	MyTurnState endTurn(Hero hero);

	MyTurnState addCardToMyHand(PlayableCard card);
	MyTurnState addCardToEnemyHand(PlayableCard card);

}
