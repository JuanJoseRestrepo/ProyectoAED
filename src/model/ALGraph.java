package model;

import java.util.List;

public class ALGraph<T> implements IGraph<T>{
	
	private List<VertexL<T>> vertexs;

	public ALGraph() {
	}

	public List<VertexL<T>> getVertexs() {
		return vertexs;
	}

	public void setVertexs(List<VertexL<T>> vertexs) {
		this.vertexs = vertexs;
	}

	@Override
	public void add(T toAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T toDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T consult(String theVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void BFS(String origin) {
		// TODO Auto-generated method stub
		
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
	public void connect(T one, T two, double weight) {
		// TODO Auto-generated method stub
		
	} 
}
