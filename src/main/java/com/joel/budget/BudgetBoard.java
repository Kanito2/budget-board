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

import javax.swing.*;

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
		frame.setSize(600, 400);

		JPanel jp = new JPanel(new GridLayout(0, 6));
		JLabel label = new JLabel("Hello World");
		JLabel label2 = new JLabel("Hello World");
		jp.add(label);
		jp.add(label2);
		frame.getContentPane().add(jp, BorderLayout.NORTH);

		ImagePanel ip = new ImagePanel(data);
		frame.getContentPane().add(ip, BorderLayout.CENTER);

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

					// for (File file : fileList) {
					// System.out.println("file dropped: " +
					// file.getAbsolutePath());
					// }

					new NordeaParser(fileList);
					int[] data = { (int) (5 * Math.random()), (int) (5 * Math.random()), (int) (5 * Math.random()),
							(int) (5 * Math.random()), (int) (5 * Math.random()), (int) (5 * Math.random()) };
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
				int[] data = { 6, 2, 1, 0, 4, 5 };
				createAndShowGUI(data);
			}
		});
	}
}
