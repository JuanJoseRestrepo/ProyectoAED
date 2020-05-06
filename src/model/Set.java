package model;

public class Set<T> implements Comparable<Set<T>>{
	
	private Vertex<T> head;
	private Vertex<T> tail;
	
	public Set(T first) {
		super();
		Vertex<T> toAdd = new Vertex<T>(first);
		head = toAdd;
		tail = toAdd;
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

	@Override
	public int compareTo(Set<T> arg0) {
		return head.compareTo(arg0.getHead());
	}
	
	public boolean findSet(T toFind) {
		boolean finded = false;
		Vertex<T> next = head;
		while(next != null) {
			if(next.getObject().equals(toFind)) {
				finded = true;
				next = null;
			} else {
				next = next.getNext();
			}
		}
		return finded;
	}
	
	public void union(Set<T> theSet) {
		tail.setNext(theSet.getHead());
		tail = theSet.getTail();
	}
}
