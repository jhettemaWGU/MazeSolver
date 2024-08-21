

import java.util.LinkedList;

public class MazeSolver {

    static int[][] maze = {
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 3, 0, 2, 1, 1}
    };



    static LinkedList<Position> path = new LinkedList<Position>();

    public static void main(String[] args) {
        Position p = createStartPosition();
        path.push(p);

        while (true) {
            int y = path.peek().y;
            int x = path.peek().x;
            printMazeWithPath();
            maze[y][x] = 0;

            if(isValid(y+1, x)) {
                //down
                if(maze[y+1][x] == 2) {
                    System.out.println("Moved down.\nYou won!");
                    return;
                } else if(maze[y+1][x] == 1) {
                    System.out.println("Moved down");
                    path.push(new Position(y+1, x));
                    continue;
                }
            }

            if(isValid(y, x-1)) {
                //left
                if(maze[y][x-1] == 2) {
                    System.out.println("Moved left.\nYou won!");
                    return;
                } else if(maze[y][x-1] == 1) {
                    System.out.println("Moved left");
                    path.push(new Position(y, x-1));
                    continue;
                }
            }

            if(isValid(y-1, x)) {
                //up
                if(maze[y-1][x] == 2) {
                    System.out.println("Moved up.\nYou won!");
                    return;
                } else if(maze[y-1][x] == 1) {
                    System.out.println("Moved up");
                    path.push(new Position(y-1, x));
                    continue;
                }
            }

            if(isValid(y, x+1)) {
                //right
                if(maze[y][x+1] == 2) {
                    System.out.println("Moved right.\nYou won!");
                    return;
                } else if(maze[y][x+1] == 1) {
                    System.out.println("Moved right");
                    path.push(new Position(y, x+1));
                    continue;
                }
            }

            path.pop();
            System.out.println("Moved Back.");
            if(path.size() <= 0) {
                System.out.println("No path found.");
                return;
            }
        }
    }

    static Position createStartPosition() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 3) {
                    return new Position(i, j);
                }
            }
        }
        System.out.println("No Starting Position found");
        return null;
    }

    public static boolean isValid(int y, int x) {
        return y >= 0 &&
                y < maze.length &&
                x >= 0 &&
                x < maze[0].length;
    }

    static void printMazeWithPath() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                boolean onPath = false;
                for (Position p : path) {
                    if (p.y == i && p.x == j) {
                        System.out.print("P ");
                        onPath = true;
                        break;
                    }
                }
                if (!onPath) {
                    if (maze[i][j] == 1) {
                        System.out.print(". ");
                    } else if (maze[i][j] == 0) {
                        System.out.print("  ");
                    } else if (maze[i][j] == 2) {
                        System.out.print("E ");
                    } else if (maze[i][j] == 3) {
                        System.out.print("S ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
