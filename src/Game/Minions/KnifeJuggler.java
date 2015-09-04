package Game.Minions;

import Game.SummonEffects.KnifeJugglerSE;

public class KnifeJuggler extends Minion {
	public KnifeJuggler(int target) {
		super("Knife Juggler",target,2,3,2,2);
		if (target<7) addSummonEffect(new KnifeJugglerSE(14));
		else addSummonEffect(new KnifeJugglerSE(15));
	}
	
	public KnifeJuggler(Minion m) {
		super(m);
	}
}
