package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import model.Game;
import model.Move;
import model.Piece;
import model.Tile;

public class TileButton extends JButton {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Tile tile;
	public TileButton(Tile tile) {
		super();
		this.setSize(50, 50);
		this.setTile(tile);
		this.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(ArrayList<Tile> tiles : Game.getInstance().getBoard().getTiles()) {
					for(Tile t : tiles) {
						t.getTileButton().resetColor();
					}
				}
				if(tile.getPiece() != null) {
					for(Move move : tile.getPiece().getAvailableMoves()) {
						move.getTileTo().getTileButton().setBackground(Color.PINK);
					}
				}
			}
		});
	}
	public Piece getPiece() {
		return this.getTile().getPiece();
	}
	public void setPiece(Piece piece) {
		if(piece != null) {
			this.setBackground(piece.getPlayer().getColor());
			this.setText(piece.getClass().getSimpleName());
		} else {
			this.setBackground(Color.GRAY);
			this.setText("");
		}
		if(this.getTile().getPiece() != null && !this.getTile().getPiece().equals(piece))
		this.getTile().setPiece(piece);
	}
	public Tile getTile() {
		return tile;
		
	}
	public void setTile(Tile tile) {
		this.tile = tile;
		this.tile.setTileButton(this);
		this.setPiece(tile.getPiece());
		
	}
	
	
	public void resetColor() {
		this.setPiece(this.getPiece());
	}
	

}
