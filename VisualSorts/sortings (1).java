package dsa;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class sortings extends JPanel{
	public int ind=0;
	public boolean msd=false;
	public boolean lsd=false;
	public int[] array=new int[] {65,14,13,12,11,10,44,85,78,26, 5, 14,43,92,21, 22, 45, 12, 98, 43, 9,  7, 2};
	public String file=null;
	public int speed=500;
	public static void main(String[] args) throws FileNotFoundException {
		
	    JFrame frame = new JFrame("whats a prince, to a king");
	    frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 1400);
        sortings panel = new sortings();
        frame.add(panel);
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Enter text file address");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.file = textField.getText();
                //System.out.println(panel.file+" alasld"); 
                
            }
        });
        panel.add(textField);
        panel.add(button);
       
        frame.setVisible(true);
        panel.repaint();
 
        while(panel.file==null) {
        	 //System.out.println(panel.file);
        	try {Thread.sleep(1);} catch (InterruptedException e1) {e1.printStackTrace();}
        } 
        //Scanner scanner = new Scanner(new File(panel.file)).useDelimiter( ",|\\n" );;
        ArrayList<Integer> arr=new ArrayList<Integer>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(panel.file))) {
            String line;            
            while ((line = br.readLine()) != null) {
            	  line = line.trim();
            	 if (line.isEmpty()) {
                     continue;
                 }
                 
                String[] tokens = line.split(",");                
                for (String token : tokens) {
                    try {
                        arr.add(Integer.parseInt(token.trim()));
                    } catch (NumberFormatException e) {
                        arr.add(Integer.parseInt(token.substring(1)));
                       
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            JSlider slider = new JSlider(100, 1000);
            slider.setMajorTickSpacing(10);  
            slider.setMinorTickSpacing(1);      
            slider.addChangeListener(e -> {
                panel.speed=slider.getValue();
            });
            panel.add(slider);
        panel.array=new int[arr.size()]; for(int i=0;i<arr.size();i++) panel.array[i]=arr.get(i);
        JButton sort1 = new JButton("MSD"); sort1.setLayout(null);
        JButton sort2 = new JButton("LSD"); sort2.setLayout(null);
        panel.add(sort1);  panel.add(sort2);
        panel.revalidate();
        //sort1.setBounds(new Rectangle(10, 10, 70, 20));  sort2.setBounds(new Rectangle(1300, 10, 70, 20));
        sort1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                panel.MSD(panel.array, panel.getGraphics(), panel.speed); 
            }
        });
        sort2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                panel.LSD(panel.array, panel.getGraphics(), panel.speed);  
            }
        });
        
        while (true) {
        	//System.out.println(arr.size());
        	//panel.repaint();
        }
	}
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	       // System.out.println("A");
	        
	        
	    }

	

	public void MSD(int[] ls, Graphics g, int speed) {
		ind=0;
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(2));
	    g.setColor(Color.white);
	    g2.fillRect(0, 100, 1800, 900);
	    g.setColor(Color.black);
	    //g2.drawLine(...);   //thick
		//g.drawRect(0, 0, 100,100);
		//System.out.println("LALALALALA");
		int max=0;

		for(int x:ls)
			max=Math.max(max, (int)(Math.log10(x))+1 );
		Trees root=new Trees(0, 750, 400, 0);
		
		for(int x:ls) {
			g.setColor(Color.white);
			g.fillRect(740, 290, 30,20);
			g.setColor(Color.black);
			g.drawString( ((Integer)(x)).toString() , 750, 300);
			int[] digits=new int[max];

			int indx=0;

			while(x!=0) {digits[indx]=x%10; x/=10; indx++; }
			Trees curr=root;
			try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
			for(int i=max-1;i>=0;i--) {
				g2.setColor(Color.blue);
				g2.drawOval(curr.coords[0], curr.coords[1], 10*i+20, 10*i+20);
				g2.setColor(Color.black);
				g2.drawString( (""+curr.dig) , curr.coords[0]+ 5*i+7, curr.coords[1]+ 5*i+12);
				try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
				g2.drawOval(curr.coords[0], curr.coords[1], 10*i+20, 10*i+20);
				g2.drawLine(curr.coords[0]+5*i+10, curr.coords[1]+10*i+20, curr.coords[0]+(int)( 14*(digits[i]-4.5)*(Math.pow(10, i)))+5*i+5, curr.coords[1]+100);
				if(curr.kids[digits[i]]==null) { 
					curr.kids[digits[i]]=new Trees(digits[i], curr.coords[0]+(int)(14* (digits[i]-4.5)*(Math.pow(10, i))), curr.coords[1]+100, digits[i]);
				}
				curr=curr.kids[digits[i]];
				if(i==0) {
					curr.plicity++;
				}
			}
			g2.setColor(Color.blue);
			g2.drawOval(curr.coords[0], curr.coords[1], 15, 15);
			g2.setColor(Color.black);
			g2.drawString( (""+curr.dig) , curr.coords[0]+4, curr.coords[1]+9);
			try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
			g2.drawOval(curr.coords[0], curr.coords[1], 15, 15);
			
			g2.setColor(Color.white);
			
			g2.fillRect(curr.coords[0], curr.coords[1]+15, 15, 15);
			g2.setColor(Color.black);
			g2.drawString( (""+curr.plicity) , curr.coords[0]+4, curr.coords[1]+25);


		}

		this.func(ls, root, 0, g, max-1);

	}

	

	public void func(int[] ls, Trees dad, int curr, Graphics g, int j) {
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(2));
		boolean dead=true;
		if(j!=-1) {
		g2.setColor(Color.red);
		g2.drawOval(dad.coords[0], dad.coords[1], 10*j+20, 10*j+20);
		g2.setColor(Color.black);
		try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
		g2.drawOval(dad.coords[0], dad.coords[1], 10*j+20, 10*j+20);
		}
		else {
			g2.setColor(Color.red);
			g2.drawOval(dad.coords[0], dad.coords[1], 15, 15);
			g2.setColor(Color.black);
			try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
			g2.drawOval(dad.coords[0], dad.coords[1], 15, 15);
		}

		for(int i=0;i<10;i++) {

			if(dad.kids[i]!=null) {

				func(ls, dad.kids[i], 10*curr+i, g,j-1);

				dead=false;

			}

		}

		if(dead) {
			for(int i=0;i<dad.plicity;i++) {
				try {Thread.sleep(speed);} catch (InterruptedException e) {e.printStackTrace();}
				g2.drawString(curr+"", 200+ind*(1000)/ls.length, 750);
				//ls[ind]=curr; 
				ind++;
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			}

		}

	}

public static void LSD(int[] ls, Graphics g, int speed) {
	Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2));
    g.setColor(Color.white);
    g2.fillRect(0, 100, 1800, 900);
    g.setColor(Color.black);
	int max=0;
	int[] tosort=ls.clone();
	for(int x:ls)
		max=Math.max(max, (int)(Math.log10(x))+1 );
	int[] counts=new int[10];
	int[] temp=new int[ls.length];
	for(int i=0;i<max;i++) {
		
		  g.setColor(Color.white); g.fillRect(0, 310, 1300, 40); g.fillRect(0, 180, 1000, 40);
			 g.setColor(Color.black);
			 g.drawString("current digit: "+i, 0, 200);
		counts=new int[10];
		g.drawString("Counts:", 100, 600);
		for(int d=0;d<10;d++) { g.drawString(d+"", 170+70*d, 600);    g.drawString(0+"", 170+70*d, 630);   }
		for(int d=0;d<tosort.length;d++) { g.setColor(Color.white) ; 
		g.fillRect(70+(1000/tosort.length)*d, 285, 20,20);   g.setColor(Color.black) ;
			g.drawString(tosort[d]+"", 70+(1000/tosort.length)*d, 300);
		
		}
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<tosort.length;j++) {
			counts[nth(tosort[j], i)]++;
			for(int d=0;d<10;d++) { g.drawString(d+"", 170+70*d, 600); g.setColor(Color.white) ; 
				g.fillRect(170+70*d, 615, 20,20);   g.setColor(Color.black) ;
				g.drawString(counts[d]+"", 170+70*d, 630);}     g.setColor(Color.blue); 
				g.drawString(tosort[j]+"", 70+(1000/tosort.length)*j, 300);     g.setColor(Color.black); 
				
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			//170+70*d
		for(int k=1;k<10;k++) {
			counts[k]+=counts[k-1];
			g.setColor(Color.white) ; 
			g.fillRect(170+70*k, 615, 20,20);   g.setColor(Color.black) ;
			g.drawString(counts[k]+"", 170+70*k, 630);
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=tosort.length-1;j>=0;j--) {
			temp[counts[nth(tosort[j], i)]-- -1]=tosort[j];
			g.drawString(temp[counts[nth(tosort[j], i)]]+"", 70+(1000/tosort.length)*(counts[nth(tosort[j], i)]), 330); //g.setColor(Color.white); 
			//g.drawString(tosort[j]+"", 70+(1000/tosort.length)*j, 300);     g.setColor(Color.black); 
			g.setColor(Color.white) ; 
			g.fillRect(170+70*nth(tosort[j], i), 615, 20,20); g.fillRect(70+(1000/tosort.length)*j, 290, 15,15);   g.setColor(Color.black) ;
				g.drawString(counts[nth(tosort[j], i)]+"", 170+70*nth(tosort[j], i), 630);
				
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=0;j<ls.length;j++) {
			tosort[j]=temp[j];
		}
		
	}
}
public static int nth(int n, int d) {
	for(int i=0;i<d;i++)
		n/=10;
	return n%10;
}





public static int[] readIntegersFromFile(String filePath) {
    ArrayList<Integer> numbers = new ArrayList<>();

    try (Scanner scanner= new Scanner(new File(filePath))) {
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + filePath);
        e.printStackTrace();
    }
    int[] intArray =new int[numbers.size()];
    for (int i=0;i<numbers.size();i++) {
        intArray[i]=numbers.get(i);
    }
    return intArray;
}
}





