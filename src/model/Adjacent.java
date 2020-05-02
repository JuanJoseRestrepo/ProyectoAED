package model;

public class Adjacent<T> {
	
	private VertexL<T> vertex;
	private double weight;
	
	public Adjacent(VertexL<T> vertex, double weight) {
		super();
		this.vertex = vertex;
		this.weight = weight;
	}

	public VertexL<T> getVertex() {
		return vertex;
	}

	public void setVertex(VertexL<T> vertex) {
		this.vertex = vertex;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
