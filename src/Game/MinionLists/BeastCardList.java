package Game.MinionLists;

import java.util.ArrayList;

import Game.Card;
import Game.PlayableCard;
import Game.Minions.AngryChicken;
import Game.Minions.BloodfenRaptor;
import Game.Minions.Boar;
import Game.Minions.CoreHound;
import Game.Minions.HungryCrab;
import Game.Minions.IronfurGrizzly;
import Game.Minions.OasisSnapjaw;
import Game.Minions.RiverCrocolisk;
import Game.Minions.ScavengingHyena;
import Game.Minions.SilverbackPatriarch;
import Game.Minions.StarvingBuzzard;
import Game.Minions.StonetuskBoar;
import Game.Minions.TimberWolf;
import Game.Minions.TundraRhino;
import Game.Minions.Webspinner;
import Game.Minions.YoungDragonhawk;

public class BeastCardList {

	public BeastCardList() {
		list.add(new Acidmaw());
		list.add(new AngryChicken());
		list.add(new ArmoredWarhorse());
		list.add(new BloodfenRaptor());
		list.add(new CaptainsParrot());
		list.add(new CapturedJormungar());
		list.add(new CoreHound());
		list.add(new CoreRager());
		list.add(new DireWolfAlpha());
		list.add(new Dreadscale());
		list.add(new EmperorCobra());
		list.add(new Gahzrilla());
		list.add(new HauntedCreeper());
		list.add(new HungryCrab());
		list.add(new IronbeakOwl());
		list.add(new IronfurGrizzly());
		list.add(new JunglePanther());
		list.add(new KingKrush());
		list.add(new KingMukla());
		list.add(new KingOfBeasts());
		list.add(new KingsElekk());
		list.add(new LostTallstrider());
		list.add(new Maexxna());
		list.add(new Malorne());
		list.add(new MuklasChampion());
		list.add(new OasisSnapjaw());
		list.add(new RiverCrocolisk());
		list.add(new SavageCombatant());
		list.add(new SavannahHighmane());
		list.add(new ScavengingHyena());
		list.add(new SilverbackPatriarch());
		list.add(new StampedingKodo());
		list.add(new StarvingBuzzard());
		list.add(new StonetuskBoar());
		list.add(new StranglethornTiger());
		list.add(new TheBeast());
		list.add(new TimberWolf());
		list.add(new TundraRhino());
		list.add(new Webspinner());
		list.add(new YoungDragonhawk());
	}
	
	private ArrayList<PlayableCard> list = new ArrayList<PlayableCard>();

	public ArrayList<PlayableCard> get() {
		return list;
	}
	
}
