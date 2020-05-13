package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MAGraph<T> implements IGraph<T>{
	
	private int[][] matrix;
	private List<VertexM<T>> vertexs;
	private int time;
	private List<Set<T>> sets;
	private List<Edge<T>> edges;
	
	public MAGraph() {
		matrix = new int[50][50];
		vertexs = new ArrayList<VertexM<T>>();
		sets = new ArrayList<Set<T>>();
		edges = new ArrayList<Edge<T>>();
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public List<VertexM<T>> getVertexs() {
		return vertexs;
	}
	public void setVertexs(List<VertexM<T>> vertexs) {
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
		VertexM<T> m = new VertexM<T>(toAdd);
		if(vertexs.isEmpty()) {
			vertexs.add(m);
			m.setPosition(0);
		}else {
			int position = 0;
			for(int i = 0; i < vertexs.size();i++) {
				if(vertexs.get(i).getPosition() == position) {
					i = 0;
					position++;
				}
			}
			vertexs.add(m);
			m.setPosition(position);	
		}	
	}
	
	@Override
	public void delete(T toDelete) {
		boolean finded = false;
		for(int i = 0; i < vertexs.size() && !finded; i++) {
			if(vertexs.get(i).getObject().equals(toDelete)) {
				finded = true;
				int position = vertexs.get(i).getPosition();
				for(int j = 0; j < matrix.length; j++) {
					matrix[position][j] = 0;
					matrix[j][position] = 0;
				}
				vertexs.remove(i);
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void BFS(T origin) {
		Queue<VertexM<T>> q = new LinkedList<VertexM<T>>();
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
			VertexM<T> u = q.poll();
			int row = u.getPosition();
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[row][i] > 0) {
					boolean finded = false;
					for(int j = 0; j < vertexs.size() && !finded; j++) {
						if(vertexs.get(j).getPosition() == i) {
							finded = true;
							if(vertexs.get(j).getColor().equals("WHITE")) {
								vertexs.get(j).setColor("GRAY");
								vertexs.get(j).setDistance(u.getDistance()+1);
								vertexs.get(j).setPredecessor(u);
								q.offer(vertexs.get(j));
							}
						}
					}
				}
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
	
	public void DFSVisit(VertexM<T> vertexM) {
		time++;
		vertexM.setDistance(time);
		vertexM.setColor("GRAY");
		int row = vertexM.getPosition();
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[row][i] > 0) {
				for(int j = 0; j < vertexs.size();j++) {
					if(vertexs.get(j).getColor().equals("WHITE")) {						
						vertexs.get(j).setPredecessor(vertexM);
						DFSVisit(vertexs.get(j));
					}
				}
			}
		}
		vertexM.setColor("BLACK");
		time++;
		vertexM.setF(time);
		
	} 
	
	@Override
	public int prim(T origin) {
		
		
		return 0;
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

	@Override
	public void connect(T one, T two, int weight) {
		Edge<T> toAdd = new Edge<T>(one, two, weight);
		edges.add(toAdd);
		VertexM<T> one1 = null;
		VertexM<T> two2 = null;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(one)) {
				one1 = vertexs.get(i);
			} else if(vertexs.get(i).getObject().equals(two)) {
				two2 = vertexs.get(i);
			}
		}
		matrix[one1.getPosition()][two2.getPosition()] = weight;
		matrix[two2.getPosition()][one1.getPosition()] = weight;
	}
	
	public String printPath(T originO, T destinyO) {
		VertexM<T> origin = null;
		VertexM<T> destiny = null;
		for(int i = 0; i < vertexs.size(); i++) {
			if(vertexs.get(i).getObject().equals(originO)) {
				origin = vertexs.get(i);
			} else if(vertexs.get(i).getObject().equals(destinyO)) {
				destiny = vertexs.get(i);
			}
		}
		if(destiny.equals(origin)) {
			return origin.toString();
		} else if(destiny.getPredecessor() == null) {
			return "There is not path";
		} else {
			return printPath(originO, destiny.getPredecessor().getObject())+" -> " + destiny.toString(); 
		}
	}

	@Override
	public void Dijkstra(T origin) {
		PriorityQueue<VertexM<T>> pq = new PriorityQueue<VertexM<T>>();
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
			VertexM<T> u = pq.poll();
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[u.getPosition()][i] > 0) {
					VertexM<T> adjacent = null;
					for(int j = 0; j < vertexs.size(); j++) {
						if(vertexs.get(j).getPosition() == i) {
							adjacent = vertexs.get(j);
						}
					}
					int alt = u.getDistance() + matrix[u.getPosition()][i];
					if(alt < adjacent.getDistance()) {
						adjacent.setDistance(alt);
						adjacent.setPredecessor(u);
						pq.remove(adjacent);
						pq.add(adjacent);
					}
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
					if(matrix[i][j] == 0) {
						min[i][j] = Integer.MAX_VALUE;
					} else {
						min[i][j] = matrix[i][j];
					}
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
