import java.awt.*;
import java.util.LinkedList;

public class MazePath {
    private int[][] maze;
    private LinkedList<Position> path;
    private int tileSize;
    private Panel panel;


    public MazePath(int[][] maze, int tileSize, Panel panel) {
        this.maze = maze;
        this.path = new LinkedList<>();
        this.tileSize = tileSize;
        this.panel = panel;
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
                System.out.println("Destination Reached!");
                path.push(curr);
                panel.repaint();
                return true;
            } else if (maze[y][x] == 1) {
                System.out.println("Found a valid square to move to at: " + x + ", " + y);
                path.push(curr);
                panel.repaint();
                try {
                    Thread.sleep(200);
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
        panel.repaint();

        while (!path.isEmpty()) {
            Position current = path.peek();
            int y = current.y;
            int x = current.x;
            maze[y][x] = 4;

            boolean moved = false;

            if (moveTo(y+1, x)) {
                System.out.println("Moved down");
                moved = true;
            }
            if (moveTo(y, x-1)) {
                System.out.println("Moved left");
                moved = true;
            }
            if (moveTo(y-1, x)) {
                System.out.println("Moved up");
                moved = true;
            }
            if (moveTo(y, x+1)) {
                System.out.println("Moved right");
                moved = true;
            }

            if (!moved) {
                System.out.println("Go back.");
                path.pop();
                panel.repaint();
            }

            if (!path.isEmpty() && maze[path.peek().y][path.peek().x] == 2) {
                return true;
            }
        }

        System.out.println("No path found");
        return false;
    }


}
