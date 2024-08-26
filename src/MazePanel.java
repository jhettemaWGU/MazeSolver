import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MazePanel extends JPanel{

    private MazeGenerator generator;
    private Maze maze;
    private MazePath mazePath;
    private int[][] mazeArray;
    private LinkedList<Position> path;

    final int originalTileSize = 16;
    final int scale = 3;
    private int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;


    public MazePanel(int[][] mazeArray, int tileSize) {
        this.mazeArray = mazeArray;
        this.tileSize = tileSize;
        this.mazePath = new MazePath(mazeArray, tileSize, this);
        this.path = mazePath.getPath();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        generator = new MazeGenerator(maxScreenRow, maxScreenCol);
        mazeArray = generator.generateMaze();
        maze = new Maze(mazeArray, tileSize);
        path = mazePath.getPath();
    }

    public void setMaze(int[][] newMaze) {
        this.mazeArray = newMaze;
        this.maze = new Maze(mazeArray, tileSize);
        this.mazePath = new MazePath(mazeArray, tileSize, this);
        this.path = mazePath.getPath();
        repaint();
    }

    private void drawMaze(Graphics g) {
        for (int row = 0; row < mazeArray.length; row++) {
            for (int col = 0; col < mazeArray[row].length; col++) {
                if (mazeArray[row][col] == 1) {
                    g.setColor(Color.WHITE);
                } else if (mazeArray[row][col] == 3) {
                    g.setColor(Color.GREEN);
                } else if (mazeArray[row][col] == 2) {
                    g.setColor(Color.RED);
                } else if (mazeArray[row][col] == 4){
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }

    private void drawPath(Graphics g) {
        g.setColor(Color.BLUE);
        for (Position pos : path) {
            g.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
        }
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

    public int[][] getMaze(){
        return mazeArray;
    }

    public int getMazeWidth() {
        return mazeArray[0].length * tileSize;
    }

    public int getMazeHeight() {
        return mazeArray.length * tileSize;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawMaze(g);
        drawPath(g);
    }
}
