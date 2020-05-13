package model;

public class City {
	
	private ALGraph<Station> adjacentList;
	private MAGraph<Station> adjacentMatrix;
	
	public City() {
		adjacentList = new ALGraph<Station>();
		adjacentMatrix = new MAGraph<Station>();
	}

	public ALGraph<Station> getAdjacentList() {
		return adjacentList;
	}

	public void setAdjacentList(ALGraph<Station> adjacentList) {
		this.adjacentList = adjacentList;
	}

	public MAGraph<Station> getAdjacentMatrix() {
		return adjacentMatrix;
	}

	public void setAdjacentMatrix(MAGraph<Station> adjacentMatrix) {
		this.adjacentMatrix = adjacentMatrix;
	}
	
	public void addStation(String name) {
		Station toAdd = new Station(name);
		adjacentList.add(toAdd);
		adjacentMatrix.add(toAdd);
	}
	
	public void connect(String first, String second, int weight) {
		Station theFirst = null;
		Station theSecond = null;
		for(int i = 0; i < adjacentList.getVertexs().size(); i++) {
			if(adjacentList.getVertexs().get(i).getObject().toString().equals(first)) {
				theFirst = adjacentList.getVertexs().get(i).getObject();
			} else if(adjacentList.getVertexs().get(i).getObject().toString().equals(second)) {
				theSecond = adjacentList.getVertexs().get(i).getObject();
			}
		}
		adjacentList.connect(theFirst, theSecond, weight);
		adjacentMatrix.connect(theFirst, theSecond, weight);
	}
	
	public void delete(String name) {
		Station toDelete = null;
		boolean finded = false;
		for(int i = 0; i < adjacentList.getVertexs().size() && !finded; i++) {
			if(adjacentList.getVertexs().get(i).getObject().toString().equals(name)) {
				toDelete = adjacentList.getVertexs().get(i).getObject();
				finded = true;
			}
		}
		adjacentList.delete(toDelete);
		adjacentMatrix.delete(toDelete);
	}
	
	public String minimumDistanceEdges(String origin, String destiny) {
		String msg = "";
		Station originS = null;
		Station destinyS = null;
		for(int i = 0; i < adjacentList.getVertexs().size(); i++) {
			if(adjacentList.getVertexs().get(i).getObject().toString().equals(origin)) {
				originS = adjacentList.getVertexs().get(i).getObject();
			} else if(adjacentList.getVertexs().get(i).getObject().toString().equals(destiny)) {
				destinyS = adjacentList.getVertexs().get(i).getObject();
			}
		}
		adjacentList.BFS(originS);
		adjacentMatrix.BFS(originS);
		msg += "With the adjacent list:"+"\n";
		msg += adjacentList.printPath(originS, destinyS) + "\n";
		msg += "With the adjacent matrix:"+"\n";
		msg += adjacentMatrix.printPath(originS, destinyS) + "\n";
		return msg;
	}
	
	public String minimumDistanceWeight(String origin, String destiny) {
		String msg = "";
		Station originS = null;
		Station destinyS = null;
		for(int i = 0; i < adjacentList.getVertexs().size(); i++) {
			if(adjacentList.getVertexs().get(i).getObject().toString().equals(origin)) {
				originS = adjacentList.getVertexs().get(i).getObject();
			} else if(adjacentList.getVertexs().get(i).getObject().toString().equals(destiny)) {
				destinyS = adjacentList.getVertexs().get(i).getObject();
			}
		}
		adjacentList.Dijkstra(originS);
		adjacentMatrix.Dijkstra(originS);
		msg += "With the adjacent list:"+"\n";
		msg += adjacentList.printPath(originS, destinyS) + "\n";
		msg += "With the adjacent matrix:"+"\n";
		msg += adjacentMatrix.printPath(originS, destinyS) + "\n";
		return msg;
	}
}
