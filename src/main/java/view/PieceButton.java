package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Piece;

public class PieceButton extends JButton {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Piece piece;
	public PieceButton(Piece piece) {
		super();
		this.setSize(50, 50);
		this.setPiece(piece);
		this.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		if(piece != null) {
			this.setBackground(piece.getPlayer().getColor());
			this.setText(piece.getClass().getSimpleName());
		} else {
			this.setBackground(Color.GRAY);
			this.setText("");
		}
		
		this.piece = piece;
	}
	
	
	
	

}
