/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.GameController;
import controller.ThreadController;


public class Game {

	private static Game instance = null;

	private int aiDepth = 3;
	private List<Player> players = new ArrayList<>();
	private Board board;
	private MinMaxNode root;
	private MinMaxNode actualRoot;
	private Player currentPlayer;
	private int currentPlayerCounter = 0;
	private Move bestMove = null;
	private Tile killTile;
	private Piece currentSelectedPiece;

	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	private Game() {
		instance = this;
		this.board = new Board();
		this.players.add( new Player(0, 0));
		this.players.add( new Player(this.board.getColumnCount()-1, 0));
		
		this.players.add( new Player(this.board.getColumnCount()-1, this.board.getRowCount()-1));
		
		this.players.add( new Player(0, this.board.getRowCount()-1));
		this.players.get(0).setColor(Color.BLUE);
		this.players.get(1).setColor(Color.RED);
		this.players.get(2).setColor(Color.GREEN);
		this.players.get(3).setColor(Color.YELLOW);
		this.currentPlayer = this.players.get(0);
		this.root = new MinMaxNode();
		this.actualRoot = this.root;
		
	}
	
	public Player nextPlayer() {
		this.currentPlayerCounter++;
		this.currentPlayerCounter %= this.players.size();
		Player temp = this.players.get(currentPlayerCounter);
		if(temp.isDead()) {
			return nextPlayer();
		}
		this.currentPlayer = temp;
		GameController.getInstance().send(new PlayerChangedEvent(currentPlayer));
		return this.currentPlayer;
	}
	
	public Player nextPlayer(MinMaxNode movePlayed) {
		Player previousPlayer = this.currentPlayer;
		this.currentPlayerCounter++;
		this.currentPlayerCounter %= this.players.size();
		Player temp = this.players.get(currentPlayerCounter);
		if(temp.isDead()) {
			return nextPlayer(movePlayed);
		}
		this.currentPlayer = temp;
		GameController.getInstance().send(new PlayerChangedEvent(currentPlayer, previousPlayer, movePlayed));
		return this.currentPlayer;
	}
	
	public Player previousPlayer() {
		this.currentPlayerCounter--;
		this.currentPlayerCounter %= this.players.size();
		
		if(this.currentPlayerCounter < 0) {
			this.currentPlayerCounter += players.size();
		}
		Player temp = this.players.get(currentPlayerCounter);
		if(temp.isDead()) {
			return previousPlayer();
		}
		this.currentPlayer = temp;
		return this.currentPlayer;
	}


	public int getAiDepth() {
		return aiDepth;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public Board getBoard() {
		return board;
	}
	public Game setAiDepth(int aiDepth) {
		this.aiDepth = aiDepth;
		return this;
	}
	public Game setBoard(Board board) {
		this.board = board;
		return this;
	}
	public Game linkPlayers(Player _players) {
		if (_players != null) {
			getPlayers().add(_players);
		}
		return this;
	}
	public Game linkBoard(Board _board) {
		setBoard(_board);
		return this;
	}
	public Game unlinkPlayers(Player _players) {
		if (_players != null) {
			getPlayers().remove(_players);
		}
		return this;
	}
	public Game unlinkPlayers(Iterator<Player> it) {
		it.remove();
		return this;
	}
	public Game unlinkBoard() {
		setBoard(null);
		return this;
	}

	public Player nextPlayer(Player player) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).equals(player)) {
				return this.getPlayer(i+1);
			}
		}
		return null;
	}
	public Player getPlayer(int i) {
		i = i % players.size();
		if(i < 0) {
			i += players.size();
		}
		return this.players.get(i);
	}
	public MinMaxNode getRoot() {
		return root;
		
	}
	public void setRoot(MinMaxNode root) {
		this.root = root;
		
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
		
	}
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		
	}
	public int getCurrentPlayerCounter() {
		return currentPlayerCounter;
		
	}
	public void setCurrentPlayerCounter(int currentPlayerCounter) {
		this.currentPlayerCounter = currentPlayerCounter;
		
	}
	public MinMaxNode getActualRoot() {
		return actualRoot;
		
	}
	public void setActualRoot(MinMaxNode actualRoot) {
		this.actualRoot = actualRoot;
		
	}
	
	public Player getLordPlayer() {
		for(Player p : this.players) {
			if(p.getChief().isCenter() && !p.isDead()) {
				return p;
			}
		}
		return null;
	}
	public Move getBestMove() {
		return bestMove;
		
	}
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
		
	}
	public Piece getCurrentSelectedPiece() {
		return currentSelectedPiece;
		
	}
	public void setCurrentSelectedPiece(Piece currentSelectedPiece) {
		this.currentSelectedPiece = currentSelectedPiece;
		
	}
	public Tile getKillTile() {
		return killTile;
		
	}
	public void setKillTile(Tile killTile) {
		this.killTile = killTile;
		
	}
}