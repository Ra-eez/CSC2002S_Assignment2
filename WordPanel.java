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
                private static Score score;
                //private int usedWord;

		public synchronized int useWord(){
                    
                    while (availableWords[counter]==true){
                        counter++;
                    }
                    
                    availableWords[counter] = true;
                    //counter--;
                    return counter;
                }
                public void wordMatch(String text){
                 //   if (words[usedWord].matchWord(text)){
                 for (int i = 0; i < noWords; i++){
                     if (words[i].matchWord(text)){
                         words[i].resetWord();
                        repaint();
                    }}
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
		
		WordPanel(WordRecord[] words, int maxY, Score score) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;
                        this.score = score;
                        availableWords = new boolean[words.length];
                        for (int i = 0; i < words.length; i++){
                            availableWords[i] = false;
                        }
		}
		
		public void run() {
			//add in code to animate this
                            //useWord();
                            int usedWord = useWord();
                          //  System.out.println(usedWord + "");
                          //  System.out.println(words[usedWord].getWord());
                            
                            while (words[usedWord].dropped()==false){
                                
                                words[usedWord].drop(10);
                               // System.out.println(usedWord + "but its hereeeeee");
                               try {
                                   
                                    Thread.sleep(words[usedWord].getSpeed());
                                    repaint(); 
                                    
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (words[usedWord].dropped()==true){
                                    words[usedWord].resetWord();
                                    score.missedWord();
                                    repaint();
                            }
                            
		}

	}
}


