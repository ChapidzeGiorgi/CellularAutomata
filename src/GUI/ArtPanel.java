package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class ArtPanel extends JPanel {
	private BufferedImage image;
	private Rule rule;
	private Random random = new Random();
	private static final long serialVersionUID = 1L;

	private static final int ONCOLOR = 0x0FF00;
	private static final int OFFCOLOR = 0x006400;

	public ArtPanel(Rule rule) {
		this.rule = rule;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		if (image == null || image.getWidth() != width || image.getHeight() != height) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}
		boolean on = true;

		for (int i = 0; i < width; i++) {
			image.setRGB(i, 0, on ? ONCOLOR : OFFCOLOR);
			if (random.nextInt(20) == 0) {
				on = !on;
			}
		}
		
		for(int y = 1; y < height; y++) {
			for(int x = 0; x < height; x++) {
				
				int xLeft = x == 0 ? width - 1 : x - 1;
				int xRight = x == width - 1 ? 0 : x + 1;
				
				int leftColor = image.getRGB(xLeft, y - 1) & 0xFFFFFF;
				int centerColor = image.getRGB(x, y - 1) & 0xFFFFFF;
				int rightColor = image.getRGB(xRight, y - 1) & 0xFFFFFF;
			
				int left = leftColor == ONCOLOR ? 1 : 0;
				int center = centerColor == ONCOLOR ? 1 : 0;
				int right = rightColor == ONCOLOR ? 1 : 0;
				
				int value = (left << 2) | (center << 1) | right;
				int pixelOn = rule.get(value);
				
				image.setRGB(x, y, pixelOn == 1 ? ONCOLOR : OFFCOLOR);
			}
			
		}
		
		g.drawImage(image, 0, 0, null);
	}
}
