package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ALGraph<T> implements IGraph<T>{
	
	private List<VertexL<T>> vertexs;

	public ALGraph() {
		vertexs = new ArrayList<VertexL<T>>();
	}

	public List<VertexL<T>> getVertexs() {
		return vertexs;
	}

	public void setVertexs(List<VertexL<T>> vertexs) {
		this.vertexs = vertexs;
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	} 
}
