package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ALGraph<T> implements IGraph<T>{
	
	private List<VertexL<T>> vertexs;
	private int time;

	public ALGraph() {
		vertexs = new ArrayList<VertexL<T>>();
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
	public List prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List Kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
			return printPath(origin, destiny.getPredecessor())+" "+destiny.toString(); 
		}
	}
}
