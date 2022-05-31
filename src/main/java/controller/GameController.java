package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Chief;
import model.Game;
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
		Game game = Game.getInstance();
		load.send(game);
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
