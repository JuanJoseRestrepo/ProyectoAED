package model;

import java.util.ArrayList;
import java.util.List;

public class MAGraph<T> implements IGraph<T>{
	
	private int[][] matrix;
	private List<VertexM<T>> vertexs;
	
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
		VertexM<T> m = new VertexM<T>(toDelete);
		for(int i = 0; i < vertexs.size();i++) {
			if(m.getPosition() == vertexs.get(i).getPosition()) {
				vertexs.remove(i);
			}
		}
		
		for(int i = 0; i < matrix.length;i++) {
			matrix[i][m.getPosition()] = 0;
			matrix[m.getPosition()][i] = 0;
		}

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
	public void connect(T one, T two, int weight) {
		VertexM<T> one1 = new VertexM<T>(one);
		VertexM<T> two2 = new VertexM<T>(two);
		
		matrix[one1.getPosition()][two2.getPosition()] = weight;
		matrix[two2.getPosition()][one1.getPosition()] = weight;
		
	}
}
