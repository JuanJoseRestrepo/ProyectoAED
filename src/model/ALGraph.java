package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import sun.security.provider.certpath.AdjacencyList;

public class ALGraph<T> implements IGraph<T>{
	
	private List<VertexL<T>> vertexs;
	private int time;
	private List<Set<T>> sets;

	public ALGraph() {
		vertexs = new ArrayList<VertexL<T>>();
		sets = new ArrayList<Set<T>>();
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
	public List prim(T node) {
		//Como retorna la lista simplemente recorremos el grafo que nos genera y ahi sacamos el valor del peso
		VertexL<T> u = new VertexL<T>(node);
        PriorityQueue<Adjacent<T>> pq = new PriorityQueue<>();
        List<Adjacent<T>> adjVertex = u.getAdjacents();
        
        int n = adjVertex.size();
        
        boolean visited[] = new boolean[n];
        for(int i = 0; i < n;i++) {
        	visited[i] = false;
        }
        
		for(Adjacent<T> v :adjVertex) {
			Adjacent<T> m = new Adjacent<T>(v.getVertex(),v.getWeight());
			pq.add(m);
		}
		
		int inTree = 1;
		
		visited[0] = true;

		while(!pq.isEmpty() && inTree < n) {
			
			Adjacent<T> s = pq.poll();
			
			if(!visited[s.getVertex().getDistance()]) {
				
				inTree++;
				visited[s.getVertex().getDistance()] = true;
	
				
				for(int i = 0; i < adjVertex.size();i++) {
					pq.add(adjVertex.get(s.getVertex().getDistance()));
				}
				
			}

		}
		
		return adjVertex;
	}

	@Override
	public List Kruskal() {
		// TODO Auto-generated method stub
		return null;
	}


	public void connect(T one, T two, int weight) {
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
	public int[][] floydWarshall(T origin) {
		VertexL<T> v1 = (VertexL<T>) origin;
		int adjacents = v1.getAdjacents().size();
		
		int[][] min = new int[v1.getAdjacents().size()][v1.getAdjacents().size()];
		for(int edge = 0; edge < vertexs.size();edge++) {
			min[edge][edge] = 0;
		}
		for(int o = 0; o < adjacents;o++) {
			int m = v1.getAdjacents().get(o).getVertex().getDistance();
			min[m][m] = v1.getAdjacents().get(o).getWeight();
		}
		
		for(int k = 0; k < vertexs.size();k++) {
			for(int i = 0; i < vertexs.size();i++) {
				for(int j = 0; j < vertexs.size();j++) {
					if(min[i][j] > min[i][k] + min[k][j]) {
						min[i][j] = min[i][k] + min[k][j];
					}
				}
			}
		}
		
		
		
		return min;
		
	}
}
