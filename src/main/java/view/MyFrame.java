package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.GameController;
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
				GameController.getInstance().fireAllRules();
			}
		});
		this.add(fireRules);
	}

}