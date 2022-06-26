/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;


public class Player implements Killable {
	private ArrayList<Piece> pieces = new ArrayList<>();
	
	private Color color;


	public Player(int x, int y) {
		int i = 1;
		int j = 1;
		if(x == 0 && y == 0) {

		} else if(x == Game.getInstance().getBoard().getColumnCount()-1 && y == 0) {
			i = -1;
		} else if(y == Game.getInstance().getBoard().getRowCount() - 1 && x == 0) {
			j = -1;
		} else {
			i = -1;
			j = -1;
		}
		this.pieces.add(new Chief(x, y, this));
		this.pieces.add(new Assasin(x+i, y, this));
		this.pieces.add(new Reporter(x, y+j, this));
		this.pieces.add(new Diplomat(x+i, y+j, this));
		this.pieces.add(new Necromobile(x+i+i, y+j+j, this));
		this.pieces.add(new Millitant(x+i+i, y, this));
		this.pieces.add(new Millitant(x+i+i, y+j, this));
		this.pieces.add(new Millitant(x, y+j+j, this));
		this.pieces.add(new Millitant(x+i, y+j+j, this));

	}


	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public Player linkPieces(Piece _pieces) {
		if (_pieces != null) {
			_pieces.unlinkPlayer();
			_pieces.setPlayer(this);
			getPieces().add(_pieces);
		}
		return this;
	}
	public Player unlinkPieces(Piece _pieces) {
		if (_pieces != null) {
			_pieces.setPlayer(null);
			getPieces().remove(_pieces);
		}
		return this;
	}
	public Player unlinkPieces(Piece _pieces, Iterator<Piece> it) {
		if (_pieces != null) {
			_pieces.setPlayer(null);
			it.remove();
		}
		return this;
	}
	@Override
	public boolean isDead() {
		return this.getChief().isDead();
	}


	public Color getColor() {
		return color;
		
	}


	public void setColor(Color color) {
		this.color = color;
		
	}
	
	public int getIndex() {
		int index = 0;
		for(Player p : Game.getInstance().getPlayers()) {
			if(p.equals(this)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	
	public Chief getChief() {
		return (Chief) this.pieces.get(0);
	}
}