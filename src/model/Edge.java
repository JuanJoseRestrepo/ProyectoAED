package model;

public class Edge<T> {
	
	private T first;
	private T second;
	private int weight;
	
	public Edge(T first, T second, int weight) {
		super();
		this.first = first;
		this.second = second;
		this.weight = weight;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
