package model;

public class Adjacent<T> {
	
	private VertexL<T> vertex;
	private int weight;
	
	public Adjacent(VertexL<T> vertex, int weight) {
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
