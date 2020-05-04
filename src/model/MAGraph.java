package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
