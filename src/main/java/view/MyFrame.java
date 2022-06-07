package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.DroolsController;
import net.miginfocom.swing.MigLayout;

public class MyFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame() {
		this.setSize(1100, 1100);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.setLayout(new MigLayout());
		JButton fireRules = new JButton("Fire Rules");
		
		fireRules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DroolsController.getInstance().fireAllRules();
				System.out.println("test");
			}
		});
		this.add(fireRules, "wrap");

		this.add(BoardPanel.getInstance(), "center");

	}

}
