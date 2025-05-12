package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {

    private BufferedImage roadImage;
    private BufferedImage carImage;
    private PlayerCar car;

    private int roadOffsetY = 0;
    private int scrollSpeed = 4;

    public Game() {
       	roadImage = SpriteLoader.load("src/images/road_straight.png");
       	BufferedImage carSprite = SpriteLoader.load("src/images/player_car_topdown.png");
       	
       	int screenWidth = 800;  
        int screenHeight = 800;

        int boundsWidth = 600;
        int boundsHeight = 400;
  
        int boundsX = (screenWidth - boundsWidth) / 2;
        int boundsY = (screenHeight - boundsHeight) / 2;

        Rectangle carBounds = new Rectangle(boundsX, boundsY, boundsWidth, boundsHeight);
       	car = new PlayerCar(carSprite, carBounds);

       	carImage = carSprite; 
    }

    public void update(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
        roadOffsetY += scrollSpeed;
        if (roadOffsetY >= roadImage.getHeight()) {
            roadOffsetY = 0;
        }

        car.update(upPressed, downPressed, leftPressed, rightPressed);
    }

    public void draw(Graphics2D g, int screenW, int screenH) {
        int y = -roadImage.getHeight() + roadOffsetY;
        while (y < screenH) {
            g.drawImage(roadImage, 0, y, screenW, roadImage.getHeight(), null);
            y += roadImage.getHeight();
        }
         car.draw(g);
    }
}
