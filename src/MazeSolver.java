

public class MazeSolver {

    public static void main(String[] args) {
        Panel panel = new Panel();
        int tileSize = panel.getTileSize();
        Maze maze = new Maze(panel.getMazeArray(), tileSize);
        Position start = maze.getStartPosition(panel.getMazeArray());
        start.print();
        panel.startPanel();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        MazePath path = new MazePath(panel.getMazeArray(), tileSize, panel);
        path.findPath(start);
        panel.drawCompletedPath(path.getPath());
        panel.repaint();
    }
}
