/**
 * 
 */
package com.joel.budget;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Joel
 *
 */

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417945731616876267L;
	private BufferedImage image;
	private int[] data;

	public ImagePanel(int[] data) {
		this.data = data;
		try {
			image = ImageIO.read(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"1000-f.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 0, y = 0;

		for (int elem : data) {
			y = 0;
			for (int i = 0; i < elem; i++) {
				g.drawImage(image, x, y, 100, 50, this);
				y += 50;
			}
			x += 100;
		}
	}

}