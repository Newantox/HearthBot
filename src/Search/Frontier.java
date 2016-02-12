package Search;

public interface Frontier {
	abstract void add(Node n);
	abstract void clear();
	abstract boolean empty();
	abstract Node remove();
	abstract int maxNodes();
	abstract double getMinionWeight();
	abstract double getHpWeight();
}
