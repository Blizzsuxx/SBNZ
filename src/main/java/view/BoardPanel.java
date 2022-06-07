package view;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.Game;
import net.miginfocom.swing.MigLayout;

public class BoardPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<PieceButton>> pieces = new ArrayList<ArrayList<PieceButton>>();
	
	
	private static BoardPanel instance = null;
	
	public static BoardPanel getInstance() {
		if(instance == null) {
			instance = new BoardPanel();
		}
		return instance;
	}
	
	private BoardPanel() {
		super(new MigLayout());
		for(int i = 0; i < Game.getInstance().getBoard().getColumnCount(); i++) {
			this.pieces.add(new ArrayList<PieceButton>());
			for(int j = 0; j < Game.getInstance().getBoard().getRowCount(); j++) {
				
				this.pieces.get(i).add(new PieceButton(Game.getInstance().getBoard().getTile(i, j).getPiece()));
				this.add(this.pieces.get(i).get(j),"height 100:100:100, width 100:100:100, cell " + i + " " + j );
			}
		}
	}

	public ArrayList<ArrayList<PieceButton>> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<ArrayList<PieceButton>> pieces) {
		this.pieces = pieces;
	}

}
