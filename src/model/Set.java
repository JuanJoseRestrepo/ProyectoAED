package model;

public class Set<T> {
	
	private Vertex<T> head;
	private Vertex<T> tail;
	
	public Set() {
		super();
	}

	public Vertex<T> getHead() {
		return head;
	}

	public void setHead(Vertex<T> head) {
		this.head = head;
	}

	public Vertex<T> getTail() {
		return tail;
	}

	public void setTail(Vertex<T> tail) {
		this.tail = tail;
	}
}
