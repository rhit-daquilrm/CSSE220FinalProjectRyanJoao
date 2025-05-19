package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {

	private BufferedImage roadImage;
	private BufferedImage carImage;
	private PlayerCar car;
	private ObstacleManager obstacleManager;

	private int roadOffsetY = 0;
	private int baseScrollSpeed = 4;

	private int frameCount = 0;
	private int level = 0;

	private boolean gameStarted = false;
	private boolean gameOver = false;

	private int score = 0;
	private int scoreTimer = 0;
	private int highScore = 0;

	public Game() {
		roadImage = SpriteLoader.load("src/images/road.png");
		carImage = SpriteLoader.load("src/images/playerCar.png");

		int screenWidth = 800;
		int screenHeight = 800;
		int boundsWidth = screenWidth - 200;
		int boundsHeight = screenHeight - 50;

		int boundsX = (screenWidth - boundsWidth) / 2;
		int boundsY = (screenHeight - boundsHeight) / 2;

		Rectangle carBounds = new Rectangle(boundsX, boundsY, boundsWidth, boundsHeight);
		car = new PlayerCar(carImage, carBounds);

		obstacleManager = new ObstacleManager(boundsWidth);
	}

	public void update(boolean up, boolean down, boolean left, boolean right) {
		if (!gameStarted || gameOver)
			return;

		frameCount++;

		if (frameCount % 600 == 0) {
			level++;
			obstacleManager.increaseDifficulty();
		}

		int scrollSpeed = baseScrollSpeed + obstacleManager.getGlobalSpeedBoost();
		roadOffsetY += scrollSpeed;
		if (roadOffsetY >= roadImage.getHeight()) {
			roadOffsetY = 0;
		}

		car.update(up, down, left, right);
		obstacleManager.update();

		for (Obstacle o : obstacleManager.getObstacles()) {
			if (car.getBounds().intersects(o.getBounds())) {
				gameOver = true;
				if (score > highScore) {
					highScore = score;
				}
				break;
			}
		}
		scoreTimer++;
		if (scoreTimer >= 60) {
			score += 5;
			scoreTimer = 0;
		}
	}

	public void draw(Graphics2D g, int screenW, int screenH) {
		g.setColor(Color.DARK_GRAY); 
		g.fillRect(0, 0, screenW, screenH);
		int y = -roadImage.getHeight() + roadOffsetY;
		while (y < screenH) {
			g.drawImage(roadImage, 0, y, screenW, roadImage.getHeight(), null);
			y += roadImage.getHeight();
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Score:", 10, 30);
		g.drawString("   " + score, 10, 55);
		g.drawString("Best:", 10, 80);
		g.drawString("   " + highScore, 10, 105);

		obstacleManager.draw(g);
		car.draw(g);

		if (!gameStarted) {
			g.setColor(new Color(0, 0, 0, 180));
			g.fillRect(0, 0, screenW, screenH);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 48));
			g.drawString("INFINITE ROAD DRIVE", screenW / 2 - 270, screenH / 2 - 20);

			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Press SPACE to Start", screenW / 2 - 130, screenH / 2 + 40);
		}

		if (gameOver) {
			g.setColor(new Color(0, 0, 0, 180));
			g.fillRect(0, 0, screenW, screenH);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 48));
			g.drawString("GAME OVER", screenW / 2 - 150, screenH / 2 - 20);

			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Final Score: " + score, screenW / 2 - 100, screenH / 2 + 20);
			g.drawString(" High Score: " + highScore, screenW / 2 - 100, screenH / 2 + 60);
			g.drawString("Press SPACE to Try Again", screenW / 2 - 140, screenH / 2 + 100);

		}

	}

	public void startGame() {
		gameStarted = true;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void reset() {
		roadOffsetY = 0;
		frameCount = 0;
		level = 0;
		gameOver = false;
		gameStarted = true;
		score = 0;
		scoreTimer = 0;

		int screenWidth = 800;
		int screenHeight = 800;
		int boundsWidth = 600;
		int boundsHeight = 750;

		int boundsX = (screenWidth - boundsWidth) / 2;
		int boundsY = (screenHeight - boundsHeight) / 2;
		Rectangle carBounds = new Rectangle(boundsX, boundsY, boundsWidth, boundsHeight);

		car = new PlayerCar(carImage, carBounds);
		obstacleManager = new ObstacleManager(boundsWidth);
	}

}
