package Game;

public class TruckObstacle extends Obstacle {
	public TruckObstacle(int x, int y, int accel) {
		super(SpriteLoader.load("src/images/truck.png"), x, y, 6 + accel, 60, 160);
	}
}
