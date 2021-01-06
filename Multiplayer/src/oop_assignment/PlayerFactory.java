package oop_assignment;

public class PlayerFactory {
	
	public Player createPlayer(Game game, int id, String type) {
		Player player = null;
		
		if(type.equals("subscribed")) {
			player = new SubscribedPlayer(game, id);
		} else if (type.equals("unsubscribed")) {
			player = new UnsubscribedPlayer(game, id);
		}
		
		return player;
	}

}
