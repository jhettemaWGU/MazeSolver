import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Panel extends JPanel {

    MazeGenerator generator = new MazeGenerator(12, 16);

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    final int[][] mazeArray = generator.generateMaze();

            /*{
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
    };*/
    Maze maze = new Maze(mazeArray, tileSize);
    MazePath mazePath = new MazePath(mazeArray, tileSize, this);
    LinkedList<Position> path = mazePath.getPath();

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
        window.add(this);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        maze.draw(g);
        drawPath(g);
    }

    public void drawPath(Graphics g) {
        if (g != null) {
            g.setColor(Color.BLUE);
            for (Position pos : path) {
                g.setColor(Color.BLUE);
                g.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
            }
        }
    }
}
