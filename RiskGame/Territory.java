package dsa;

import java.util.HashMap;

public class Territory {
	public int sold;
	public HashMap<Territory, Integer> map;
	public String name;
	public Territory(int sold, HashMap<Territory, Integer> map, String name) {
		super();
		this.sold = sold;
		this.map = map;
		this.name = name;
	}
}
