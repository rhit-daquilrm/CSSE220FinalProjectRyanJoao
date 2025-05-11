package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game {

    private BufferedImage roadImage;
    private BufferedImage carImage;

    private int roadOffsetY = 0;
    private int scrollSpeed = 4;

    public Game() {
        try {
            roadImage = ImageIO.read(new File("src/images/road_straight.png"));
            carImage = ImageIO.read(new File("src/images/player_car_topdown.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        roadOffsetY += scrollSpeed;
        if (roadOffsetY >= roadImage.getHeight()) {
            roadOffsetY = 0;
        }
    }

    public void draw(Graphics2D g, int screenW, int screenH) {
        
        int y = -roadImage.getHeight() + roadOffsetY;
        while (y < screenH) {
            g.drawImage(roadImage, 0, y, screenW, roadImage.getHeight(), null);
            y += roadImage.getHeight();
        }

        
        int carX = (screenW - carImage.getWidth()) / 2;
        int carY = (screenH - carImage.getHeight()) / 2;
        g.drawImage(carImage, carX, carY, null);
    }
}
