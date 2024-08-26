import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {

    private int[][] maze;
    private int rows;
    private int cols;
    private Random random;


    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.random = new Random();
    }

    public int[][] generateMaze() {
        do {
            maze = new int[rows][cols];

            for(int i = 0; i<rows; i++) {
                for(int j = 0; j<cols; j++) {
                    maze[i][j] = random.nextInt(2);
                }
            }

            int startRow = random.nextInt(rows);
            maze[startRow][0] = 3;

            int endRow = random.nextInt(rows);
            maze[endRow][cols - 1] = 2;

        } while (!isValidMaze());
        for (int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }

        return maze;
    }

    private boolean isValidMaze() {
        int[][] mazeCopy = copyMaze(maze);
        MazePath pathFinder = new MazePath(mazeCopy, 1, null);
        Position startPosition = getStartPosition(mazeCopy);
        startPosition.print();
        return pathFinder.findPath(startPosition);
    }

    private Position getStartPosition(int[][] maze) {
        for (int i = 0; i < rows; i++) {
            if (maze[i][0] == 3) {
                return new Position(i, 0);
            }
        }
        System.out.println("You attempted to generate a maze without a start position.");
        return null;
    }

    private int[][] copyMaze(int[][] originalMaze) {
        int[][] copy = new int[originalMaze.length][originalMaze[0].length];
        for (int i = 0; i < originalMaze.length; i++) {
            System.arraycopy(originalMaze[i], 0, copy[i], 0, originalMaze[i].length);
        }
        return copy;
    }
}
