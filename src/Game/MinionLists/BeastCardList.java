package Game.MinionLists;

import java.util.ArrayList;

import Game.Card;
import Game.Cards.Minions.AngryChickenCard;
import Game.Cards.Minions.BloodfenRaptorCard;
import Game.Cards.Minions.BoarCard;
import Game.Cards.Minions.CoreHoundCard;
import Game.Cards.Minions.HungryCrabCard;
import Game.Cards.Minions.IronfurGrizzlyCard;
import Game.Cards.Minions.OasisSnapjawCard;
import Game.Cards.Minions.RiverCrocoliskCard;
import Game.Cards.Minions.ScavengingHyenaCard;
import Game.Cards.Minions.SilverbackPatriarchCard;
import Game.Cards.Minions.StarvingBuzzardCard;
import Game.Cards.Minions.StonetuskBoarCard;
import Game.Cards.Minions.TimberWolfCard;
import Game.Cards.Minions.TundraRhinoCard;
import Game.Cards.Minions.WebspinnerCard;
import Game.Cards.Minions.YoungDragonhawkCard;

public class BeastCardList {

	public BeastCardList() {
		list.add(new AcidmawCard());
		list.add(new AngryChickenCard());
		list.add(new ArmoredWarhorseCard());
		list.add(new BloodfenRaptorCard());
		list.add(new CaptainsParrotCard());
		list.add(new CapturedJormungarCard());
		list.add(new CoreHoundCard());
		list.add(new CoreRagerCard());
		list.add(new DireWolfAlphaCard());
		list.add(new DreadscaleCard());
		list.add(new EmperorCobraCard());
		list.add(new GahzrillaCard());
		list.add(new HauntedCreeperCard());
		list.add(new HungryCrabCard());
		list.add(new IronbeakOwlCard());
		list.add(new IronfurGrizzlyCard());
		list.add(new JunglePantherCard());
		list.add(new KingKrushCard());
		list.add(new KingMuklaCard());
		list.add(new KingOfBeastsCard());
		list.add(new KingsElekkCard());
		list.add(new LostTallstriderCard());
		list.add(new MaexxnaCard());
		list.add(new MalorneCard());
		list.add(new MuklasChampionCard());
		list.add(new OasisSnapjawCard());
		list.add(new RiverCrocoliskCard());
		list.add(new SavageCombatantCard());
		list.add(new SavannahHighmaneCard());
		list.add(new ScavengingHyenaCard());
		list.add(new SilverbackPatriarchCard());
		list.add(new StampedingKodoCard());
		list.add(new StarvingBuzzardCard());
		list.add(new StonetuskBoarCard());
		list.add(new StranglethornTigerCard());
		list.add(new TheBeastCard());
		list.add(new TimberWolfCard());
		list.add(new TundraRhinoCard());
		list.add(new WebspinnerCard());
		list.add(new YoungDragonhawkCard());
	}
	
	private ArrayList<Card> list = new ArrayList<Card>();

	public ArrayList<Card> get() {
		return list;
	}
	
}
