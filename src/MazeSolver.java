

public class MazeSolver {

    public static void main(String[] args) {
        Panel panel = new Panel();
        System.out.println("Starting a new panel...");
        int tileSize = panel.getTileSize();
        System.out.println("Tile size: " + tileSize);
        Maze maze = new Maze(panel.getMazeArray(), tileSize);
        Position start = maze.getStartPosition(panel.getMazeArray());
        start.print();
        panel.startPanel();
        System.out.println("Ran startPanel");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        MazePath path = new MazePath(panel.getMazeArray(), tileSize, panel);
        path.findPath(start);
        panel.repaint();
    }
}
