import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Panel extends JPanel {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    final int[][] mazeArray = {
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0},
            {3, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 2},
    };
    Maze maze = new Maze(mazeArray, tileSize);

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startPanel() {
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze Solver");
        Panel panel = new Panel();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public int getTileSize() {
        return tileSize;
    }

    public int[][] getMazeArray() {
        return mazeArray;
    }

    public void drawSingleSquare(Position pos) {
        Graphics g = getGraphics();
        if (g != null) {
            g.setColor(Color.BLUE);
            g.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.draw(g);
        //drawPath(g);
    }

    public void drawCompletedPath(LinkedList<Position> path) {
        Graphics g = getGraphics();
        if (g != null) {
            g.setColor(Color.BLUE);
            for (Position pos : path) {
                g.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
            }
            g.dispose();
        }
    }
}
