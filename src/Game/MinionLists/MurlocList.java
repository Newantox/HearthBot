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
	
		public MurlocList(int target) {
			list.add(new BluegillWarrior(target));
			list.add(new ColdlightOracle(target));
			list.add(new ColdlightSeer(target));
			list.add(new GrimscaleOracle(target));
			list.add(new Murloc(target));
			list.add(new MurlocKnight(target));
			list.add(new MurlocRaider(target));
			list.add(new MurlocScout(target));
			list.add(new MurlocTidecaller(target));
			list.add(new MurlocTidehunter(target));
			list.add(new MurlocWarleader(target));
			list.add(new OldMurkEye(target));
			list.add(new Puddlestomper(target));
			list.add(new SiltfinSpiritwalker(target));
;		}
		
		private ArrayList<Minion> list = new ArrayList<Minion>();

		public ArrayList<Minion> get() {
			return list;
		}

}
