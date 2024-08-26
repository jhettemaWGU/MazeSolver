import java.awt.*;
import java.util.LinkedList;

public class MazePath {
    private int[][] maze;
    private LinkedList<Position> path;
    private int tileSize;
    private MazePanel mazePanel;


    public MazePath(int[][] maze, int tileSize, MazePanel mazePanel) {
        this.maze = maze;
        this.path = new LinkedList<>();
        this.tileSize = tileSize;
        this.mazePanel = mazePanel;
    }

    public LinkedList<Position> getPath() {
        return path;
    }

    private boolean isValid(int y, int x){
        return  y >= 0 &&
                y < maze.length &&
                x >= 0 &&
                x < maze[0].length;
    }

    private boolean moveTo(int y, int x) {
        if (isValid(y, x)) {
            Position curr = new Position(y, x);
            if (maze[y][x] == 2) {
                path.push(curr);
                if (mazePanel != null) mazePanel.repaint();
                return true;
            } else if (maze[y][x] == 1) {
                path.push(curr);
                if (mazePanel != null) mazePanel.repaint();
                try {
                    Thread.sleep(0);// changed from 200
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                return true;
            }
        }
        return false;
    }

    public boolean findPath(Position start) {
        if (start == null) {
            System.out.println("No starting position found");
            return false;
        }
        path.push(start);
        if (mazePanel != null) mazePanel.repaint(); // to avoid a Null Pointer Exception when I do call this method to find a potential maze path.

        while (!path.isEmpty()) {
            Position current = path.peek();
            int y = current.y;
            int x = current.x;
            maze[y][x] = 4;

            boolean moved = false;

            if (moveTo(y-1, x)) {
                moved = true;
            }
            if (moveTo(y+1, x)) {
                moved = true;
            }
            if (moveTo(y, x-1)) {
                moved = true;
            }

            if (moveTo(y, x+1)) {
                moved = true;
            }

            if (!moved) {
                path.pop();
                if (mazePanel != null) mazePanel.repaint();
            }

            if (!path.isEmpty() && maze[path.peek().y][path.peek().x] == 2) {
                return true;
            }
        }

        System.out.println("No path found");
        return false;
    }


}
