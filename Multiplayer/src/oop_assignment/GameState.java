package oop_assignment;

import java.util.ArrayList;

public class GameState {
	private Thread moderator;
	private ArrayList<Player> listOfPlayers;
	private ArrayList<Thread> listOfPlayersThread;
	private ArrayList<Integer> listOfGeneratedNumbers;
	private boolean playerReadStatus[];
	private boolean isGameCompleted;
	private PlayerFactory playerFactory;
	
	GameState(Game game, int noOfPlayers){
		this.moderator = new Thread(Moderator.getInstance(game));
		this.playerFactory = new PlayerFactory();
		this.listOfPlayersThread = new ArrayList<Thread>();
		this.listOfPlayers = new ArrayList<Player>();
		for(int i=1 ;i<=noOfPlayers; i++) {
			Player new_player = playerFactory.createPlayer(game, i, "unsubscribed");
			listOfPlayers.add(new_player);
			listOfPlayersThread.add(new Thread(new_player));
		}
		listOfGeneratedNumbers = new ArrayList<Integer>();
		playerReadStatus = new boolean[noOfPlayers];
		setPlayerReadStatus(true);
		isGameCompleted = false;
	}
	
	public boolean isAllTrue() {
		for(int i=0 ;i<playerReadStatus.length; i++) {
			if(playerReadStatus[i]== false)
				return false;
		}
		return true;
	}
	
	public void addNewNumber(int number) {
		listOfGeneratedNumbers.add(number);
	}
	
	public int readNextNumber() {
		try {
			return listOfGeneratedNumbers.get(listOfGeneratedNumbers.size()-1);
		}
		catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			return -1;
		}
	}

	//getter-setter methods
	
	public Thread getModerator() {
		return moderator;
	}

	public ArrayList<Player> getListOfPlayers() {
		return listOfPlayers;
	}

	public ArrayList<Thread> getListOfPlayersThread() {
		return listOfPlayersThread;
	}

	public void setPlayerReadStatus(boolean bool) {
		for(int i=0 ;i<playerReadStatus.length; i++) {
			playerReadStatus[i] = bool;
		}
	}
	
	public boolean hasPlayerRead(int id) {
		return playerReadStatus[id-1];
	}
	
	public void setPlayerRead(int id, boolean bool) {
		playerReadStatus[id-1]=bool;
	}
	
	public boolean isGameCompleted() {
		return isGameCompleted;
	}

	public void setGameCompleted(boolean isGameCompleted) {
		this.isGameCompleted = isGameCompleted;
	}
	
	
	

}
