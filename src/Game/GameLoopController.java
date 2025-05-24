package Game;

import javax.swing.*;

public class GameLoopController {

	private Game game;
	private GameViewer viewer;

	public GameLoopController() {
		game = new Game();
		viewer = new GameViewer(game);
	}

	public void start() {
		JFrame frame = new JFrame("Infinite Road Drive");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(viewer);
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		viewer.setFocusable(true);
		viewer.requestFocusInWindow();

		Timer timer = new Timer(16, e -> {
			game.update(viewer.isUpPressed(), viewer.isDownPressed(), viewer.isLeftPressed(), viewer.isRightPressed());
			viewer.refresh();
		});
		timer.start();
	}
}