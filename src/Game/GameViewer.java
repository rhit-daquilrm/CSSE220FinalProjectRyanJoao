package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameViewer extends JPanel {

    private Game game;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GameViewer(Game game) {
        this.game = game;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = true;
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = true;
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = true;
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = true;
                    case KeyEvent.VK_SPACE -> {
                    	if (!game.isGameStarted() && !game.isGameOver()) {
                            game.startGame();
                        } else if (game.isGameOver()) {
                            game.reset();
                        }
                        
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = false;
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = false;
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw((Graphics2D) g, getWidth(), getHeight());
    }

    public void refresh() {
        repaint();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }
}
