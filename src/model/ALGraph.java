package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import sun.security.provider.certpath.AdjacencyList;

public class ALGraph<T> implements IGraph<T>{
	
	private List<VertexL<T>> vertexs;
	private List<Edge<T>> edges;
	private int time;
	private List<Set<T>> sets;

	public ALGraph() {
		vertexs = new ArrayList<VertexL<T>>();
		sets = new ArrayList<Set<T>>();
		edges = new ArrayList<Edge<T>>();
	}
	
	public List<VertexL<T>> getVertexs() {
		return vertexs;
	}
	
	public void setVertexs(List<VertexL<T>> vertexs) {
		this.vertexs = vertexs;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Set<T>> getSets() {
		return sets;
	}

	public void setSets(List<Set<T>> sets) {
		this.sets = sets;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<T>> edges) {
		this.edges = edges;
	}

	@Override
	public void add(T toAdd) {
		VertexL<T> theVertex = new VertexL<T>(toAdd);
		vertexs.add(theVertex);
	}

	@Override
	public void delete(T toDelete) {
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(toDelete)) {
				vertexs.remove(i);
			} else {
				vertexs.get(i).deleteAdjacent(toDelete);
			}
		}
		for(int i = 0; i < edges.size(); i++) {
			if(edges.get(i).getFirst().equals(toDelete) || edges.get(i).getSecond().equals(toDelete)) {
				edges.remove(i);
			}
		}
	}

	@Override
	public T consult(String theVertex) {
		return null;
	}

	@Override
	public void BFS(T origin) {
		Queue<VertexL<T>> q = new LinkedList<VertexL<T>>();
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(origin)) {
				vertexs.get(i).setColor("GRAY");
				vertexs.get(i).setDistance(0);
				vertexs.get(i).setPredecessor(null);
				q.offer(vertexs.get(i));
			} else {
				vertexs.get(i).setColor("WHITE");
				vertexs.get(i).setDistance(Integer.MAX_VALUE);
				vertexs.get(i).setPredecessor(null);
			}
		}
		while(!q.isEmpty()) {
			VertexL<T> u = q.poll();
			List<Adjacent<T>> theAdjacents = u.getAdjacents();
			for(int i = 0; i < theAdjacents.size(); i++) {
				if(theAdjacents.get(i).getVertex().getColor().equals("WHITE")) {
					
				}
				theAdjacents.get(i).getVertex().setColor("GRAY");
				theAdjacents.get(i).getVertex().setDistance(u.getDistance()+1);
				theAdjacents.get(i).getVertex().setPredecessor(u);
				q.offer(theAdjacents.get(i).getVertex());
			}
			u.setColor("BLACK");
		}
	}

	@Override
	public void DFS() {
		for(int i = 0; i < vertexs.size(); i++) {
			vertexs.get(i).setColor("WHITE");
			vertexs.get(i).setPredecessor(null);
		}
		time = 0;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getColor().equals("WHITE")) {
				DFSVisit(vertexs.get(i));
			}
		}
	}

	public void DFSVisit(VertexL<T> u) {
		time++;
		u.setDistance(time);
		u.setColor("GRAY");
		List<Adjacent<T>> adjacents = u.getAdjacents();
		for(int i = 0; i < adjacents.size(); i++) {
			if(adjacents.get(i).getVertex().getColor().equals("WHITE")) {
				adjacents.get(i).getVertex().setPredecessor(u);
				DFSVisit(adjacents.get(i).getVertex());
			}
		}
		u.setColor("BLACK");
		time++;
		u.setF(time);
	} 
	
	@Override
	public List<Edge<T>> prim(T node) {
		//TO DO
		return null;
	}

	@Override
	public List<Edge<T>> Kruskal() {
		List<Edge<T>> a = new ArrayList<Edge<T>>();
		for(int i = 0; i < vertexs.size(); i++) {
			makeSet(vertexs.get(i).getObject());
		}
		sortEdges();
		for(int i = 0; i < edges.size(); i++) {
			if(!findSet(edges.get(i).getFirst()).equals(findSet(edges.get(i).getSecond()))) {
				a.add(edges.get(i));
				union(edges.get(i).getFirst(), edges.get(i).getSecond());
			}
		}
		return a;
	}
	
	public void sortEdges() {
		for(int i = edges.size(); i > 0; i--) {
			for(int j = 0; j < i-1; j++) {
				if(edges.get(j).getWeight() > edges.get(j+1).getWeight()) {
					Edge<T> temp = edges.get(j);
					edges.set(j, edges.get(j+1));
					edges.set(j+1, temp);
				}
			}
		}
	}

	public void connect(T one, T two, int weight) {
		Edge<T> toAdd = new Edge<T>(one, two, weight);
		edges.add(toAdd);
		VertexL<T> v1 = null;
		VertexL<T>  v2 = null;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(one)) {
				v1 = vertexs.get(i);
			} else if(vertexs.get(i).getObject().equals(two)){
				v2 = vertexs.get(i);
			}
		}
		Adjacent<T> a1 = new Adjacent(v1, weight);
		Adjacent<T> a2 = new Adjacent(v2, weight);
		v1.getAdjacents().add(a2);
		v2.getAdjacents().add(a1);
	}
	
	public String printPath(VertexL<T> origin, VertexL<T> destiny) {
		if(destiny.equals(origin)) {
			return origin.toString();
		} else if(destiny.getPredecessor() == null) {
			return "There is not path";
		} else {
			return printPath(origin, destiny.getPredecessor())+" -> "+destiny.toString(); 
		}
	}

	@Override
	public void Dijkstra(T origin) {
		PriorityQueue<VertexL<T>> pq = new PriorityQueue<VertexL<T>>();
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(origin)) {
				vertexs.get(i).setDistance(0);
			} else {
				vertexs.get(i).setDistance(Integer.MAX_VALUE);
			}
			vertexs.get(i).setPredecessor(null);
			pq.add(vertexs.get(i));
		}
		while(!pq.isEmpty()) {
			VertexL<T> u = pq.poll();
			List<Adjacent<T>> adjacents = u.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				int alt = u.getDistance() + adjacents.get(i).getWeight();
				if(alt < adjacents.get(i).getVertex().getDistance()) {
					adjacents.get(i).getVertex().setDistance(alt);
					adjacents.get(i).getVertex().setPredecessor(u);
					pq.remove(adjacents.get(i).getVertex());
					pq.add(adjacents.get(i).getVertex());
				}
			}
		}
	}

	@Override
	public void makeSet(T toAdd) {
		Set<T> s = new Set<T>(toAdd);
		sets.add(s);
	}

	@Override
	public void union(T one, T two) {
		Set<T> firstSet = findSet(one);
		Set<T> secondSet = findSet(two);
		if(firstSet.compareTo(secondSet) < 0) {
			firstSet.union(secondSet);
		} else {
			secondSet.union(secondSet);
		}
	}

	@Override
	public Set<T> findSet(T toFind) {
		Set<T> t = null;
		boolean finded = false;
		for(int i = 0; i < sets.size() && !finded; i++) {
			if(sets.get(i).findSet(toFind)) {
				t = sets.get(i);
				finded = true;
			}
		}
		return t;
	}

	@Override
	public int[][] floydWarshall() {
		int[][] min = new int[vertexs.size()][vertexs.size()];
		for(int i = 0; i < vertexs.size(); i++) {
			for(int j = 0; j < vertexs.size(); j++) {
				if(i == j) {
					min[i][j] = 0;
				} else {
					min[i][j] = vertexs.get(i).weight(vertexs.get(j));
				}
			}
		}
		for(int k = 0; k < vertexs.size(); k++) {
			for(int i = 0; i < vertexs.size(); i++) {
				for(int j = 0; j < vertexs.size(); j++) {
					if(min[i][j] > min[i][k] + min[k][j]) {
						min[i][j] = min[i][k] + min[k][j];
					}
				}
			}
		}
		return min;
	}
}
