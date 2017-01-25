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
	private BufferedImage image2;
	private int data;

	public ImagePanel(int data) {
		this.data = data;
		try {
			image = ImageIO.read(new File(
					"src" + File.separator + "main" + File.separator + "resources" + File.separator + "1000-f.jpg"));
			image2 = ImageIO.read(new File(
					"src" + File.separator + "main" + File.separator + "resources" + File.separator + "500-f.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int y = 0;

		int bills = data / 1000;
		int bills2 = (data % 1000) / 500;
		y = 0;
		for (int i = 0; i < bills; i++) {
			g.drawImage(image, 0, y, 100, 50, this);
			y += 50;
		}
		for (int i = 0; i < bills2; i++) {
			g.drawImage(image2, 0, y, 100, 50, this);
			y += 50;
		}
	}

}