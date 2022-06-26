package model;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class PlayerChangedEvent implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private Player newPlayer;
	private Player previousPlayer;
	private MinMaxNode move;
	public PlayerChangedEvent(Player newPlayer, Player previousPlayer, MinMaxNode move) {
		super();
		this.newPlayer = newPlayer;
		this.previousPlayer = previousPlayer;
		this.move = move;
	}

	

	public Player getNewPlayer() {
		return newPlayer;
		
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
		
	}

	public PlayerChangedEvent(Player newPlayer) {
		super();
		this.newPlayer = newPlayer;
	}
	
	public PlayerChangedEvent(Player newPlayer, MinMaxNode move) {
		super();
		this.newPlayer = newPlayer;
		this.move = move;
	}

	public PlayerChangedEvent() {
		
	}

	public MinMaxNode getMove() {
		return move;
		
	}

	public void setMove(MinMaxNode move) {
		this.move = move;
		
	}

	public Player getPreviousPlayer() {
		return previousPlayer;
		
	}

	public void setPreviousPlayer(Player previousPlayer) {
		this.previousPlayer = previousPlayer;
		
	}
	
}
