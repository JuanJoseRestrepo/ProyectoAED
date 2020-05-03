package model;

import java.util.ArrayList;
import java.util.List;

public class MAGraph<T> implements IGraph<T>{
	
	private int[][] matrix;
	private List<VertexM<T>> vertexs;
	private int time;
	
	public MAGraph() {
		matrix = new int[50][50];
		vertexs = new ArrayList<VertexM<T>>();
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
	public void BFS(T origin) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void DFS() {
		// TODO Auto-generated method stub
		
	}
	
	public void DFSVisit() {
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
