package oop_assignment;


public class Game {
	private final int noOfPlayers;
	public GameState currentGameState;
	
	Game(int noOfPlayers){
		this.noOfPlayers = noOfPlayers;
		this.currentGameState = new GameState(this, noOfPlayers);
		
	}
	
	public void start() {
		currentGameState.getModerator().start();	
		for(Thread player: currentGameState.getListOfPlayersThread()) {
			player.start();
		}
	}
	
	public void stop() {
		synchronized(currentGameState) {
			int winnerPlayerID = -1;
			for(Player player: currentGameState.getListOfPlayers()) {
				int playerID = player.getPlayerID();
				int playerCrossCount = player.getCrossCount();
				System.out.println("Player"+ playerID + " has count "+ playerCrossCount);
				if(playerCrossCount == 3)
					winnerPlayerID = playerID;
			}
			if(winnerPlayerID != -1) {
				System.out.println("Winner is Player"+ winnerPlayerID);
			}
			else {
				System.out.println("No Winner");
			}
			currentGameState.setGameCompleted(true);
			currentGameState.notifyAll();
		}	
		
	}
	
	 public static int generateRandomNumberInRange(int min, int max) {
		 return (int)(Math.random()*(max-min+1)+ min);
	 }
	
	 public int getNoOfPlayers() {
		 return noOfPlayers;
	 }
	 
}
