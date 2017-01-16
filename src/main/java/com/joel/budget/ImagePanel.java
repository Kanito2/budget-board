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

public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417945731616876267L;
	private BufferedImage image;

	public ImagePanel() {
		try {                
			image = ImageIO.read(new File("src\\main\\resources\\1000-f.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 100, 50, this);
	}

}