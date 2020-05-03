package model;

public class Station implements Comparable<Station>{
	
	private String name;

	public Station(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Station arg0) {
		return name.compareTo(arg0.getName());
	}
}
