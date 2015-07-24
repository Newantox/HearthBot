package Search;

public interface Search {
	abstract Node solution(State s, GoalTest g, int step);
	abstract int lastSearch();
	//abstract int getMaxNodes();
}
