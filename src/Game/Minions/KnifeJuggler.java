package Game.Minions;

import Game.SummonEffects.KnifeJugglerSE;

public class KnifeJuggler extends Minion {
	public KnifeJuggler() {
		super("Knife Juggler",2,3,2);
		addSummonEffect(new KnifeJugglerSE());
	}
	
	public KnifeJuggler(Minion m) {
		super(m);
	}
}
