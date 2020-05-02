package model;

public class City {
	
	private IGraph<Station> adjacentList;
	private IGraph<Station> adjacentMatrix;
	
	public City() {
		adjacentList = new ALGraph<Station>();
		adjacentMatrix = new MAGraph<Station>();
	}
}
