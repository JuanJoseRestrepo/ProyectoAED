package model;

public class Vertex<T> implements Comparable<Vertex<T>>{
	
	private T object;
	private Vertex<T> next;

	public Vertex(T object) {
		super();
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public Vertex<T> getNext() {
		return next;
	}

	public void setNext(Vertex<T> next) {
		this.next = next;
	}

	@Override
	public int compareTo(Vertex<T> arg0) {
		return object.toString().compareTo(arg0.getObject().toString());
	}
}
