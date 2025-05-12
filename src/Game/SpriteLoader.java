package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SpriteLoader {

	private static final HashMap<String, BufferedImage> sprites = new HashMap<>();

	public static BufferedImage load(String path) {
		if (sprites.containsKey(path)) {
			return sprites.get(path);
		}

		try {
			BufferedImage img = ImageIO.read(new File(path));
			sprites.put(path, img);
			return img;
		} catch (IOException e) {
			System.err.println("Failed to load image: " + path);
			e.printStackTrace();
			return null;
		}
	}
}
