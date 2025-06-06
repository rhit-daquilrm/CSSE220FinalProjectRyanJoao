package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerCar {

    private BufferedImage image;
    private int x, y;
    private final int width = 45;
    private final int height = 90;
    private final int speed = 10;
    private Rectangle bounds;

    public PlayerCar(BufferedImage image, Rectangle bounds) {
        this.image = image;
        this.bounds = bounds;

        this.x = bounds.x + (bounds.width - width) / 2;
        this.y = bounds.y + (bounds.height - height) / 2;
    }

    public void update(boolean up, boolean down, boolean left, boolean right) {
        if (up && y - speed >= bounds.y) {
            y -= speed;
        }
        if (down && y + speed + height <= bounds.y + bounds.height) {
            y += speed;
        }
        if (left && x - speed >= bounds.x) {
            x -= speed;
        }
        if (right && x + speed + width <= bounds.x + bounds.width) {
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
//        g.setColor(Color.RED);
//        g.drawRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getMovementBounds() {
        return bounds;
    }
}
