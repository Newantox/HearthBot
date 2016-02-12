package Game.Minions;

public class AngryChicken extends Minion {

	public AngryChicken() {
		super("Angry Chicken",1,1,1);
		setRace(Race.BEAST);
		enrages.add(new AngryChickenEnrage());
	}
	
	public AngryChicken(Minion m) {
		super(m);
	}
	
}