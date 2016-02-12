package Game.MinionLists;

import java.util.ArrayList;

import Game.Minions.BluegillWarrior;
import Game.Minions.ColdlightOracle;
import Game.Minions.ColdlightSeer;
import Game.Minions.GrimscaleOracle;
import Game.Minions.Minion;
import Game.Minions.Murloc;
import Game.Minions.MurlocKnight;
import Game.Minions.MurlocRaider;
import Game.Minions.MurlocScout;
import Game.Minions.MurlocTidecaller;
import Game.Minions.MurlocTidehunter;
import Game.Minions.MurlocWarleader;
import Game.Minions.OldMurkEye;
import Game.Minions.Puddlestomper;
import Game.Minions.SiltfinSpiritwalker;

public class MurlocList {
	
		public MurlocList() {
			list.add(new BluegillWarrior());
			list.add(new ColdlightOracle());
			list.add(new ColdlightSeer());
			list.add(new GrimscaleOracle());
			list.add(new Murloc());
			list.add(new MurlocKnight());
			list.add(new MurlocRaider());
			list.add(new MurlocScout());
			list.add(new MurlocTidecaller());
			list.add(new MurlocTidehunter());
			list.add(new MurlocWarleader());
			list.add(new OldMurkEye());
			list.add(new Puddlestomper());
			list.add(new SiltfinSpiritwalker());
;		}
		
		private ArrayList<Minion> list = new ArrayList<Minion>();

		public ArrayList<Minion> get() {
			return list;
		}

}
