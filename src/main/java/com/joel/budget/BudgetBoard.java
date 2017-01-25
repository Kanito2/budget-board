/**
 * 
 */
package com.joel.budget;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Joel
 *
 */

public class BudgetBoard {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */

	private static JFrame frame;

	private static void createAndShowGUI(int[] data) {
		// Create and set up the window.
		frame = new JFrame("BudgetBoard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);

		JPanel jp = new JPanel(new GridLayout(0, 7));
		JLabel label = new JLabel("Boende");
		JLabel label2 = new JLabel("Mat");
		JLabel label3 = new JLabel("Transport");
		JLabel label4 = new JLabel("Kläder");
		JLabel label5 = new JLabel("Sparande");
		JLabel label6 = new JLabel("Övrigt");
		JLabel label7 = new JLabel("Lån");
		jp.add(label);
		jp.add(label2);
		jp.add(label3);
		jp.add(label4);
		jp.add(label5);
		jp.add(label6);
		jp.add(label7);
		frame.getContentPane().add(jp, BorderLayout.NORTH);

		JPanel jp2 = new JPanel(new GridLayout(0, 7));
		ImagePanel ip = new ImagePanel(data[0]);
		ImagePanel ip2 = new ImagePanel(data[1]);
		ImagePanel ip3 = new ImagePanel(data[2]);
		ImagePanel ip4 = new ImagePanel(data[3]);
		ImagePanel ip5 = new ImagePanel(data[4]);
		ImagePanel ip6 = new ImagePanel(data[5]);
		ImagePanel ip7 = new ImagePanel(data[6]);
		jp2.add(ip);
		jp2.add(ip2);
		jp2.add(ip3);
		jp2.add(ip4);
		jp2.add(ip5);
		jp2.add(ip6);
		jp2.add(ip7);
		frame.getContentPane().add(jp2, BorderLayout.CENTER);

		int[] monthlyExpense = Entry.monthlyExpense(false);
		JPanel jp3 = new JPanel(new GridLayout(0, 7));
		JLabel alabel = new JLabel(monthlyExpense[0] + ":-");
		JLabel alabel2 = new JLabel(monthlyExpense[1] + ":-");
		JLabel alabel3 = new JLabel(monthlyExpense[2] + ":-");
		JLabel alabel4 = new JLabel(monthlyExpense[3] + ":-");
		JLabel alabel5 = new JLabel(monthlyExpense[4] + ":-");
		JLabel alabel6 = new JLabel(monthlyExpense[5] + ":-");
		JLabel alabel7 = new JLabel(monthlyExpense[6] + ":-");
		jp3.add(alabel);
		jp3.add(alabel2);
		jp3.add(alabel3);
		jp3.add(alabel4);
		jp3.add(alabel5);
		jp3.add(alabel6);
		jp3.add(alabel7);
		frame.getContentPane().add(jp3, BorderLayout.SOUTH);

		enableDragAndDrop();

		// Display the window.
		// frame.pack();
		frame.setVisible(true);
	}

	private static void enableDragAndDrop() {
		DropTarget target = new DropTarget(frame, new DropTargetListener() {
			public void dragEnter(DropTargetDragEvent e) {
			}

			public void dragExit(DropTargetEvent e) {
			}

			public void dragOver(DropTargetDragEvent e) {
			}

			public void dropActionChanged(DropTargetDragEvent e) {

			}

			public void drop(DropTargetDropEvent e) {
				try {
					// Accept the drop first, important!
					e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

					List<File> fileList = (List<File>) e.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);

					new NordeaParser(fileList);

					int[] data = Entry.monthlyExpense();

					frame.dispose();
					createAndShowGUI(data);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int[] data = { 0, 0, 0, 0, 0, 0, 0 };
				createAndShowGUI(data);
			}
		});
	}
}
