package Game;


import javax.swing.*;
import java.awt.*;

public class GameViewer extends JPanel {

    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw((Graphics2D) g, getWidth(), getHeight());
    }

    public void refresh() {
        repaint();
    }
}
