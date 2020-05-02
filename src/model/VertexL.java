package model;

import java.util.ArrayList;
import java.util.List;

public class VertexL<T> {
	
	private T object;
	private String color;
	private int distance;
	private VertexL<T> predecessor;
	private List<Adjacent<T>> adjacents;
	
	public VertexL(T object) {
		super();
		this.object = object;
		adjacents = new ArrayList<Adjacent<T>>();
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
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

	public VertexL<T> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(VertexL<T> predecessor) {
		this.predecessor = predecessor;
	}

	public List<Adjacent<T>> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(List<Adjacent<T>> adjacents) {
		this.adjacents = adjacents;
	}
}
