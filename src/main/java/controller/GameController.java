package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Chief;
import model.Piece;
import model.Tile;
import view.MyFrame;

public class GameController extends Thread {
	
	private Data load;
	private ThreadController threadController;
	
	public GameController(Data load, ThreadController threadController) {
		super();
		this.load = load;
		this.threadController = threadController;
	}
	
	public void run() {
		Piece piece = new Chief();
		piece.setX(4);
		piece.setY(4);

		Tile tile = new Tile();
		tile.setX(4);
		tile.setY(4);

		Tile tile2 = new Tile();
		tile2.setX(5);
		tile2.setY(4);
		load.send(piece);
		load.send(tile);
		load.send(tile2);
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		frame.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				load.send("Game Over");
			}
		});
	}

}
