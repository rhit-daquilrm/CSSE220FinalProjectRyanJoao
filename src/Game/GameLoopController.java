package Game;

import javax.swing.*;

import javax.sound.sampled.*;

import java.net.URL;

public class GameLoopController {

	private Game game;

	private GameViewer viewer;

	private Clip bgClip;

	private boolean crashPlayed = false;

	public GameLoopController() {

		game = new Game();

		viewer = new GameViewer(game);

	}

	public void start() {

		try {

			URL bgURL = getClass().getResource("/music/bg_music.wav");

			AudioInputStream bgStream = AudioSystem.getAudioInputStream(bgURL);

			bgClip = AudioSystem.getClip();

			bgClip.open(bgStream);

			bgClip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {

			e.printStackTrace();

		}

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

			game.update(

					viewer.isUpPressed(),

					viewer.isDownPressed(),

					viewer.isLeftPressed(),

					viewer.isRightPressed()

			);

			viewer.refresh();

			if (game.isGameOver() && !crashPlayed) {

				crashPlayed = true;

				if (bgClip != null && bgClip.isRunning()) {

					bgClip.stop();

					bgClip.close();

				}

				try {

					URL crashURL = getClass().getResource("/music/crash.wav");

					AudioInputStream crashStream = AudioSystem.getAudioInputStream(crashURL);

					Clip crashClip = AudioSystem.getClip();

					crashClip.open(crashStream);

					crashClip.start();

				} catch (Exception ex) {

					ex.printStackTrace();

				}

			}

		});

		timer.start();

	}

}
