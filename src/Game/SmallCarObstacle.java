package Game;

public class SmallCarObstacle extends Obstacle {
	public SmallCarObstacle(int x, int y, int accel) {
		super(SpriteLoader.load("src/images/small_car.png"), x, y, 8 + accel, 40, 65);
	}
}
