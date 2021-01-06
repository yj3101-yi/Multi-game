package oop_assignment;

import java.util.HashMap;
import java.util.Map;

public class Player implements Runnable {
	private int id;
	private int crossCount;
	private Map<Integer,Integer> playerTicket;
	private Game game;
	
	Player(Game game,int id){
		this.id = id;
		this.game = game;
		this.crossCount = 0;
		playerTicket = new HashMap<Integer,Integer>();
		System.out.print("Player"+id+" tokens are: [");
		for(int i=0 ;i<10 ;i++) {
			int newNumber = Game.generateRandomNumberInRange(0, 50);
			if(playerTicket.containsKey(newNumber)) {		
				playerTicket.put(newNumber, playerTicket.get(newNumber)+1);		//number already exists, increase the count
			}
			else {
				playerTicket.put(newNumber,1);		//add new number
			}
			System.out.print("  "+newNumber);
		}
		System.out.println("  ]");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				GameState currentGameState = game.currentGameState;
				synchronized(currentGameState){
					if(currentGameState.isGameCompleted()) {
						return;
					}
					while(currentGameState.hasPlayerRead(id)) {
						currentGameState.wait();
						if(currentGameState.isGameCompleted()) {
							return;
						}
						
					}
					int nextNumber = currentGameState.readNextNumber();
					if(playerTicket.containsKey(nextNumber) && playerTicket.get(nextNumber)>0) {
						playerTicket.put(nextNumber, playerTicket.get(nextNumber)-1);
						crossCount = crossCount+1;
					}
					if(crossCount==3) {
						game.stop();
					}
					currentGameState.setPlayerRead(id,true);
					currentGameState.notifyAll();
				}
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getPlayerID() {
		return id;
	}
	
	public int getCrossCount() {
		return crossCount;
	}

}
