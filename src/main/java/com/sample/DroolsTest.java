package com.sample;



import controller.GameController;
import model.Piece;
import model.Tile;
import view.MyFrame;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
    		
        
            // go !
            Piece piece = new Piece();
            piece.setX(4);
            piece.setY(4);
            
            Tile tile = new Tile();
            tile.setX(4);
            tile.setY(4);
            
            Tile tile2 = new Tile();
            tile2.setX(5);
            tile2.setY(4);
            GameController.getInstance().addToRules(piece);
            GameController.getInstance().addToRules(tile);
            GameController.getInstance().addToRules(tile2);
            
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
            }
 

}
