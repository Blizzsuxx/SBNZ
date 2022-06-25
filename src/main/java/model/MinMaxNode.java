/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MinMaxNode {
	private Integer[] values = {0,0,0,0};
	private Move move;
	private ArrayList<MinMaxNode> children = new ArrayList<>();
	private MinMaxNode bestChild;
	private MinMaxNode parent;
	private Player player;
	private int depth;


	public Integer[] getValues() {
		return values;
	}
	
	public ArrayList<MinMaxNode> getChildren() {
		return children;
	}
	public void addChild(MinMaxNode node) {
		this.children.add(node);
	}
	public Player getPlayer() {
		return player;
	}
	public MinMaxNode setValues(Integer[] values) {
		this.values = values;
		return this;
	}
	
	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public void setChildren(ArrayList<MinMaxNode> children) {
		this.children = children;
	}

	public MinMaxNode setPlayer(Player player) {
		this.player = player;
		return this;
	}
	public MinMaxNode linkMove(Move _move) {
		setMove(_move);
		return this;
	}
	public MinMaxNode linkChildren(MinMaxNode _children) {
		if (_children != null) {
			getChildren().add(_children);
		}
		return this;
	}
	public MinMaxNode linkPlayer(Player _player) {
		setPlayer(_player);
		return this;
	}
	public MinMaxNode unlinkMove() {
		setMove(null);
		return this;
	}
	public MinMaxNode unlinkChildren(MinMaxNode _children) {
		if (_children != null) {
			getChildren().remove(_children);
		}
		return this;
	}
	public MinMaxNode unlinkChildren(Iterator<MinMaxNode> it) {
		it.remove();
		return this;
	}
	public MinMaxNode unlinkPlayer() {
		setPlayer(null);
		return this;
	}
	public int getDepth() {
		return depth;

	}
	public void setDepth(int depth) {
		this.depth = depth;

	}
	@Override
	public String toString() {
		return "MinMaxNode [values=" + values + ", move=" + move + ", children=" + children + ", player=" + player
				+ ", depth=" + depth + "]";
	}
	public MinMaxNode getParent() {
		return parent;
		
	}
	public void setParent(MinMaxNode parent) {
		this.parent = parent;
		
	}
	public MinMaxNode getBestChild() {
		return bestChild;
		
	}
	public void setBestChild(MinMaxNode bestChild) {
		this.bestChild = bestChild;
		
	}
	
	
	public void heuristic() {
		move.execute();
		int survivors = 0;
		for(Player player : Game.getInstance().getPlayers()) {
			if(!player.isDead()) {
				survivors +=1;
			}
		}
		
		Player lordPlayer = Game.getInstance().getLordPlayer();
		for(Player player : Game.getInstance().getPlayers()) {
			if(!player.isDead() && lordPlayer == null) {
				values[player.getIndex()] = 48/survivors;
			} else if(!player.isDead() && lordPlayer.equals(player)) {
				values[player.getIndex()] = 48;
			} else {
				values[player.getIndex()] = 0;
			}
			
		}
		
		
		for(Player player : Game.getInstance().getPlayers()) {
			for(Piece piece : player.getPieces()) {
				
				if(!piece.isDead()){
					if(piece.getClass() == Millitant.class){
						values[piece.getPlayer().getIndex()] += 6;
					} else if(piece.getClass() == Necromobile.class){
						values[piece.getPlayer().getIndex()] += 12;
					} else if(piece.getClass() == Assasin.class){
						values[piece.getPlayer().getIndex()] += 18;
					} else if(piece.getClass() == Diplomat.class){
						values[piece.getPlayer().getIndex()] += 12;
					} else if(piece.getClass() == Reporter.class){
						values[piece.getPlayer().getIndex()] += 18;
					} else if(piece.getClass() == Chief.class){
						values[piece.getPlayer().getIndex()] += 30;
					} 
				} else {
					for(Player p2 : Game.getInstance().getPlayers()){
						if(!piece.getPlayer().equals(p2) && !p2.isDead()){
							if(piece.getPlayer().isDead()){
								if(piece.getClass() == Millitant.class){
									values[p2.getIndex()] += (6/survivors);
								} else if(piece.getClass() == Necromobile.class){
									values[p2.getIndex()] += (12/survivors);
								} else if(piece.getClass() == Assasin.class){
									values[p2.getIndex()] += (18/survivors);
								} else if(piece.getClass() == Diplomat.class){
									values[p2.getIndex()] +=  (12/survivors);
								} else if(piece.getClass() == Reporter.class){
									values[p2.getIndex()] += (18/survivors);
								} else if(piece.getClass() == Chief.class){
									values[p2.getIndex()] += (30/survivors);
								} 
							} else {
								if(piece.getClass() == Millitant.class){
									values[p2.getIndex()] += (6);
								} else if(piece.getClass() == Necromobile.class){
									values[p2.getIndex()] += (12);
								} else if(piece.getClass() == Assasin.class){
									values[p2.getIndex()] += (18);
								} else if(piece.getClass() == Diplomat.class){
									values[p2.getIndex()] += (12);
								} else if(piece.getClass() == Reporter.class){
									values[p2.getIndex()] += (18);
								} else if(piece.getClass() == Chief.class){
									values[p2.getIndex()] += (30);
								} 
							
							}
							
						}
					}
				
				}
			}
		}
		
		move.undo();
		
		
	}


}