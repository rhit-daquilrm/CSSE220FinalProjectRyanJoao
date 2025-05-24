package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obstacle {
	protected int x, y;
	protected int speed;
	protected BufferedImage image;
	protected int width, height;

	public Obstacle(BufferedImage image, int x, int y, int speed, int width, int height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public void update() {
		y += speed;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, width, height, null);
//	    g.setColor(Color.RED);
//	    g.drawRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean isOffScreen(int screenHeight) {
		return y > screenHeight;
	}


}
