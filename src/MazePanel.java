import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel{
    private int[][] maze;

    publice void setMaze(int[][] maze){
        this.maze = maze;
    }

    public int[][] getMaze(){
        return maze;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (maze != null) {
            int tileSize = getWidth() / maze[0].length;
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[y].length; x++) {
                    switch (maze[y][x]) {
                        case 0 -> g.setColor(Color.BLACK);
                        case 1 -> g.setColor(Color.WHITE);
                        case 2 -> g.setColor(Color.RED);
                        case 3 -> g.setColor(Color.GREEN);
                        case 4 -> g.setColor(Color.BLUE);
                    }
                    g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }
}
