/**
 * 
 */
package com.joel.budget;

import java.awt.*;
import javax.swing.*;

/**
 * @author Joel
 *
 */

public class BudgetBoard {

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("BudgetBoard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 400));

		JLabel label = new JLabel("Test");
		frame.getContentPane().add(label, BorderLayout.NORTH);

		ImagePanel ip = new ImagePanel();
		frame.getContentPane().add(ip, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
