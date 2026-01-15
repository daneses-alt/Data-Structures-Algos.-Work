package dsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class RiskDriver {

	public static void main(String[] args) {
		Risk ME= new Risk("", "");
		// TODO Auto-generated method stub
		 String solds = "SoldiersTest.csv"; // Replace with the path to your CSV file

	        try (BufferedReader br = new BufferedReader(new FileReader(solds))) {
	            String line;
	            int checkforbs=0;
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(";");	  
	                if(checkforbs++==0) {
	                	values[0]=values[0].substring(1);
	                }
	                ME.MAP.put(values[0], new Territory(Integer.parseInt(values[1]), new HashMap(), values[0] ) );
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        String Terrs = "TerrainTest.csv";
	        try (BufferedReader br = new BufferedReader(new FileReader(Terrs))) {
	            String line=br.readLine();
	            
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(";");	   
	           
	              
	                Territory a=ME.MAP.get(values[0]); 
	                Territory b=ME.MAP.get(values[1]);
	                a.map.put(b, Integer.parseInt(values[2]) ); b.map.put(a, Integer.parseInt(values[2]) );
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	        
	      
	        System.out.println("   \n\n\n\n");
	        System.out.println(ME.func("Alaska", "China"));
	        ME.whole("Egypt");
	}
}





