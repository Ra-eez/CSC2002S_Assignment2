package csc2002s_assignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
                private int counter = 0;
                private boolean[] availableWords;

		public synchronized void useWord(){
                    
                    while (availableWords[counter]==true){
                        counter++;
                    }
                    
                    availableWords[counter] = true;
                    //counter--;
                }
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//gdrawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		  }
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;
                        availableWords = new boolean[words.length];
                        for (int i = 0; i < words.length; i++){
                            availableWords[i] = false;
                        }
		}
		
		public void run() {
			//add in code to animate this
                        //for (int i = 0; i < words.length; i++){
                            //Thread b = new Thread(words[i]);
                            //words[i].drop(5);
                            //counter ++;
                            useWord();
                            System.out.println(counter);
                            while (words[counter].dropped()==false){
                                System.out.println(counter+"entered while loop");
                                words[counter].drop(10);
                                //repaint();
                                //try {
                                    System.out.println(counter+" it tried");
                                    //Thread.sleep(words[counter].getSpeed());
                                    repaint();
                                /*} catch (InterruptedException ex) {
                                    System.out.println("caught errors");
                                    Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }*/
                            }
                            //repaint();
                        //}
                       // paintComponent();
		}

	}


