package oop_assignment;

public class Moderator implements Runnable {
	private static Moderator uniqueModerator;
	private Game game;
	
	private Moderator(Game game){
		this.game = game;
	}
	
	public static synchronized Moderator getInstance(Game game) {
		if(uniqueModerator == null) {
			uniqueModerator = new Moderator(game);
		}
		return uniqueModerator;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<10; i++){
			try {
				GameState currentGameState = game.currentGameState;
				synchronized(currentGameState){
					if(currentGameState.isGameCompleted()) {
						return;
					}
					while(!currentGameState.isAllTrue()) {
						currentGameState.wait();
						if(currentGameState.isGameCompleted()) {
							return;
						}
					}
					int nextGeneratedNumber = Game.generateRandomNumberInRange(0, 50);
					currentGameState.addNewNumber(nextGeneratedNumber);
					System.out.println("Moderator generated: "+ nextGeneratedNumber);
					currentGameState.setPlayerReadStatus(false);
					currentGameState.notifyAll();
				}	
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		game.stop();
	}

}
