package Game;

public class ConeObstacle extends Obstacle {
	public ConeObstacle(int x, int y, int accel) {
		super(SpriteLoader.load("src/images/cone.png"), x, y, 4 + accel, 25, 25);
	}

}
