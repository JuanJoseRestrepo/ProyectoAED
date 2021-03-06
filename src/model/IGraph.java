package model;

import java.util.List;

public interface IGraph<T> {
	
	public void add(T toAdd);
	public void connect(T one, T two, int weight);
	public void delete(T toDelete);
	public void BFS(T origin);
	public void DFS();
	public int prim(T origin);
	public List<Edge<T>> Kruskal();
	public void Dijkstra(T origin);
	public void makeSet(T toAdd);
	public void union(T one, T two);
	public long[][] floydWarshall();
	public Set<T> findSet(T toFind);
}
