package dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Risk {
	public HashMap<String, Territory> MAP;
	public Risk(String a, String b) {
		read("");
		//func(a, b);
		MAP=new HashMap();
	}
	
	public void read(String s) {
		
	}
	
	public int func(String a, String b) {
		int res=0;
		Territory A=MAP.get(a); Territory B=MAP.get(b);
		HashMap<Territory, Integer> exp=new HashMap<>();
		HashMap<Territory, Territory> path=new HashMap<>();  // get(a)=b   means a was found by b
		exp.put(A, 0);
		//Comparator() ,...
		PriorityQueue<EDGE> pq=new PriorityQueue<>(
	            (e1, e2) -> Integer.compare(e1.cost, e2.cost)
		        );
		for(Entry<Territory, Integer> e:A.map.entrySet()) {
			//System.out.println(e.getKey().name+" and ");
			pq.add(new EDGE(A, e.getKey(), MAP.get(e.getKey().name).sold) );
		}
		while(exp.getOrDefault(B, null)==null) {
			EDGE edg=pq.poll();
			System.out.println(edg.S.name+"  edge");
			Territory ths=MAP.get(edg.E.name); int c=edg.cost; //territory and its "distance"
			if (exp.getOrDefault(ths, null)==null ) {          //check if explored/reached yet
				exp.put(ths, c);
				System.out.println("PATH: "+ths.name+", "+edg.S.name);
				path.put(ths, edg.S);
				for(Entry<Territory, Integer> e:ths.map.entrySet()) {
					//System.out.println(e.getKey().name+ "   name");
					if(exp.getOrDefault(e.getKey(), null)==null)
						pq.add(new EDGE(ths, e.getKey(), MAP.get(e.getKey().name).sold+c)); 
				}
			}
			
		}
		res=exp.get(B);
		while(A!=B) {
			System.out.println(B.name+ " <- "); B=path.get(B); 
		} System.out.println(A.name);
		return res;
	}
	
	
	public int whole(String a) {
		int res=0;
		Territory A=MAP.get(a);
		PriorityQueue<EDGE> pq=new PriorityQueue<>(
	            (e1, e2) -> Integer.compare(e1.cost, e2.cost)
		        );
		for(Entry<Territory, Integer> e:A.map.entrySet()) {
			pq.add(new EDGE(A, e.getKey(), e.getValue()));
		}
		HashSet<String> found=new HashSet<String>(); found.add(A.name);
		while(found.size()<42 ) {			//100 idfk
			EDGE curr=pq.poll();
			if(!found.contains(curr.E.name)) {
				//System.out.println("ASASASASASSSSSSSSSSSSSSSSSSSSSSSSSasssssssssssssssssssssssss");
			res+=curr.cost;
			found.add(curr.E.name);
			
			for(Entry<Territory, Integer> e:curr.E.map.entrySet()) {
				if(!found.contains(e.getKey().name)) {
					pq.add(new EDGE(curr.E, e.getKey(), e.getValue()));
				}
				else {
					//System.out.println("BOMBCLAT");
				}
			}
			}
		}
		
		System.out.println(res);
		return res;
	}
}
