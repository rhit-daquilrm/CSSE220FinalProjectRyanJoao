package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerCar {

    private BufferedImage image;
    private int x, y;
    private int speed = 10;
    private Rectangle bounds;

    public PlayerCar(BufferedImage image, Rectangle bounds) {
        this.image = image;
        this.bounds = bounds;

        this.x = bounds.x + (bounds.width - image.getWidth()) / 2;
        this.y = bounds.y + (bounds.height - image.getHeight()) / 2;
    }

    public void update(boolean up, boolean down, boolean left, boolean right) {
        if (up && y - speed >= bounds.y) {
            y -= speed;
        }
        if (down && y + speed + image.getHeight() <= bounds.y + bounds.height) {
            y += speed;
        }
        if (left && x - speed >= bounds.x) {
            x -= speed;
        }
        if (right && x + speed + image.getWidth() <= bounds.x + bounds.width) {
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
}
