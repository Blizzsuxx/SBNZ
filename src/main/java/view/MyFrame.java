package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.DroolsController;
import model.Piece;
import net.miginfocom.swing.MigLayout;

public class MyFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public MyFrame() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new MigLayout());
		JButton fireRules = new JButton("Fire Rules");
		fireRules.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DroolsController.getInstance().fireAllRules();
			}
		});
		this.add(fireRules);
		
		JButton addCenterPiece = new JButton("Add Center Piece");
		JButton addNotCenterPiece = new JButton("Add Not Center Piece");
		this.add(addCenterPiece);
		this.add(addNotCenterPiece);
		addCenterPiece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Piece p = new Piece();
				p.setX(4);
				p.setY(4);
				DroolsController.getInstance().addToRules(p);
			}
		});
		
		addNotCenterPiece.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Piece p = new Piece();
				p.setX(4);
				p.setY(3);
				DroolsController.getInstance().addToRules(p);
			}
		});
		
	}

}
