package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import controller.DroolsController;
import controller.GameController;
import model.Game;
import model.MinMaxNode;
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
				if((Game.getInstance().getCurrentSelectedPiece() == null) && Game.getInstance().getKillTile() == null) {
					resetBoardColor();
					if(tile.getPiece() != null) {
						for(MinMaxNode move : tile.getPiece().getAvailableMoves()) {
							move.getMove().getTileTo().getTileButton().setBackground(Color.PINK);
						}
						Game.getInstance().setCurrentSelectedPiece(tile.getPiece());
						Game.getInstance().setKillTile(null);
					}
					
				} else if((Game.getInstance().getCurrentSelectedPiece() != null) && Game.getInstance().getKillTile() == null) {
					resetBoardColor();
					boolean restart = true;
					for(MinMaxNode move : Game.getInstance().getCurrentSelectedPiece().getAvailableMoves()) {
						
						if(move.getMove().getTileTo().equals(tile)) {
							if(move.getMove().getMovesOnKill() != null) {
								Game.getInstance().setKillTile(tile);
								System.out.println("set kill tile to: " + tile);
								move.getMove().getMovesOnKill().getTileTo().getTileButton().setBackground(Color.pink);
								restart = false;
								continue;
							}
							move.getMove().execute();
							resetBoardColor();
							
							Game.getInstance().nextPlayer(move);
							
							DroolsController.getInstance().fireAllRules();
							break;
						}
						
					}
					if (restart) {
						Game.getInstance().setCurrentSelectedPiece(null);
						Game.getInstance().setKillTile(null);
					}
					
				} else if(Game.getInstance().getKillTile() != null) {
					resetBoardColor();
					for(MinMaxNode move : Game.getInstance().getCurrentSelectedPiece().getAvailableMoves()) {
						System.out.println(move);
						if(move.getMove().getTileTo().equals(Game.getInstance().getKillTile()) &&  move.getMove().getMovesOnKill().getTileTo().equals(tile) ) {
							System.out.println("usao usao usao");
							move.getMove().execute();
							resetBoardColor();
							
							Game.getInstance().nextPlayer(move);
							
							DroolsController.getInstance().fireAllRules();
							break;
						}
						
					}
					
					
					Game.getInstance().setCurrentSelectedPiece(null);
					Game.getInstance().setKillTile(null);
				} else {
					Game.getInstance().setCurrentSelectedPiece(null);
					Game.getInstance().setKillTile(null);
				}
				
			}
		});
	}
	public Piece getPiece() {
		return this.getTile().getPiece();
	}
	public void setPiece(Piece piece) {
		if(piece != null) {
			if(piece.isDead()) {
				this.setBackground(Color.WHITE);
			} else {
				this.setBackground(piece.getPlayer().getColor());
			}
			
			this.setText(piece.getClass().getSimpleName());
		} else if(tile.isCenter()) {
			this.setBackground(Color.BLACK);
			this.setText("");
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
	
	public void resetBoardColor() {
		for(ArrayList<Tile> tiles : Game.getInstance().getBoard().getTiles()) {
			for(Tile t : tiles) {
				t.getTileButton().resetColor();
			}
		}
		if(Game.getInstance().getBestMove() != null) {
			Game.getInstance().getBestMove().getTileFrom().getTileButton().setBackground(Color.magenta);
			Game.getInstance().getBestMove().getTileTo().getTileButton().setBackground(Color.magenta);
			if(Game.getInstance().getBestMove().getMovesOnKill() != null) {
				Game.getInstance().getBestMove().getMovesOnKill().getTileTo().getTileButton().setBackground(Color.magenta);
			}
		}
		
		
	}
	

}
