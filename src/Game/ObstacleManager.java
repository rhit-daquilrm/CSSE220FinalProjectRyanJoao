package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObstacleManager {

	private ArrayList<Obstacle> obstacles;
	private Random random;
	private int spawnCooldown = 0;
	private int spawnRate = 30;
	private int globalSpeedBoost = 0;

	private final Rectangle[] laneBounds = {
		    new Rectangle(100, 0, 100, 800),
		    new Rectangle(250, 0, 100, 800),
		    new Rectangle(405, 0, 100, 800), 
		    new Rectangle(560, 0, 100, 800)    
		};

	public ObstacleManager(int screenWidth) {
		this.obstacles = new ArrayList<>();
		this.random = new Random();
	}

	public void update() {

		spawnCooldown--;
		if (spawnCooldown <= 0) {
			spawnRandomObstacle();
			spawnCooldown = spawnRate;
		}

		Iterator<Obstacle> it = obstacles.iterator();
		while (it.hasNext()) {
			Obstacle o = it.next();
			o.update();
			if (o.isOffScreen(800)) {
				it.remove();
			}
		}
	}

	public void draw(Graphics2D g) {
		for (Obstacle o : obstacles) {
			o.draw(g);
		}
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void spawnRandomObstacle() {
		   int laneIndex = random.nextInt(laneBounds.length);
		    Rectangle lane = laneBounds[laneIndex];

		    int minX = lane.x;
		    int maxX = lane.x + lane.width;
		    int x = minX + random.nextInt(maxX - minX - 40); 

		    int type = random.nextInt(3);

		switch (type) {
		case 0 -> obstacles.add(new SmallCarObstacle(x, -100, globalSpeedBoost));
		case 1 -> obstacles.add(new TruckObstacle(x, -100, globalSpeedBoost));
		case 2 -> obstacles.add(new ConeObstacle(x, -100, globalSpeedBoost));
		}
	}

	public void increaseDifficulty() {
		if (spawnRate >= 4) {
			spawnRate -= 2;
		}
		globalSpeedBoost += 2;
	}

	public int getGlobalSpeedBoost() {
		return globalSpeedBoost;
	}
}
