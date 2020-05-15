package model;

public class VertexM<T> implements Comparable<VertexM<T>>{
	
	private T object;
	private VertexM<T> predecessor;
	private String color;
	private int position;
	private int distance;
	private int f;
	
	public VertexM(T object) {
		super();
		this.object = object;
	}

	public VertexM<T> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(VertexM<T> predecessor) {
		this.predecessor = predecessor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return object.toString();
	}

	@Override
	public int compareTo(VertexM<T> o) {
		return distance - o.distance;
	}
}
