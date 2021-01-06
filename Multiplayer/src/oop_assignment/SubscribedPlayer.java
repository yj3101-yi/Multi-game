package oop_assignment;

public class SubscribedPlayer extends Player {
	private int rewardPoints;
	private String userName;
	
	SubscribedPlayer(Game game, int id){
		super(game,id);
		this.rewardPoints = 100;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
