package dsa;



public class Trees {

	int dig;

	Trees[] kids;
	
	int plicity;
	
	int[] coords;

	public Trees(int x, int a, int b, int di) {

		dig=di;

		kids=new Trees[10];

		for(int i=0;i<10;i++) kids[i]=null;
		
		plicity=0;
		
		coords=new int[] {a,b};

	}
}