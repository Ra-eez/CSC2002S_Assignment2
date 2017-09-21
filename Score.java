package csc2002s_assignment2;

public class Score {
	private int missedWords;
	private int caughtWords;
	private int gameScore;
     //   static WordApp wordapp;
	
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
                
	}
	
	public synchronized int getMissed() {
		return missedWords;
	}

	public synchronized int getCaught() {
		return caughtWords;
	}
	
	public synchronized int getTotal() {
		return (missedWords+caughtWords);
	}

	public synchronized int getScore() {
		return gameScore;
	}
	
	public synchronized void missedWord() {
		missedWords++;
                //WordApp.setupGUI(WordApp.frameX, WordApp.frameY, WordApp.yLimit);
                WordApp.scoreUpdate();
	}

	public synchronized void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
                WordApp.scoreUpdate();
                
	}

	public synchronized void resetScore() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
}
