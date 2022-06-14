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
	private ArrayList<ArrayList<TileButton>> tiles = new ArrayList<ArrayList<TileButton>>();
	
	
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
			this.tiles.add(new ArrayList<TileButton>());
			for(int j = 0; j < Game.getInstance().getBoard().getRowCount(); j++) {
				
				this.tiles.get(i).add(new TileButton(Game.getInstance().getBoard().getTile(i, j)));
				this.add(this.tiles.get(i).get(j),"height 100:100:100, width 100:100:100, cell " + i + " " + j );
			}
		}
	}

	public ArrayList<ArrayList<TileButton>> getTiles() {
		return tiles;
	}

	public void setPieces(ArrayList<ArrayList<TileButton>> tiles) {
		this.tiles = tiles;
	}

}
