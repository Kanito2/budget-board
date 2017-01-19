/**
 * 
 */
package com.joel.budget;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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

	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("BudgetBoard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label, BorderLayout.NORTH);

		int[] data = { 8, 2, 1, 0, 4, 9 };
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

					List<File> list = new ArrayList<File>();
					list = (List<File>) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

					for (File file : list) {
						System.out.println(file.getAbsolutePath());
					}

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
				createAndShowGUI();
			}
		});
	}
}
