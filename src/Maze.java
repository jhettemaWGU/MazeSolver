import java.awt.*;

public class Maze {
    private int[][] mazeArray;
    private int tileSize;
    private Position position;
    private Position startPosition;
    //private MazePanel mazePanel;


    public Maze(int[][] mazeArray, int tileSize){
        this.mazeArray = mazeArray;
        this.tileSize = tileSize;
        //this.mazePanel = mazePanel;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getStartPosition(int[][] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (mazeArray[i][j] == 3) {
                    return new Position(i, j);
                }
            }
        }
        System.out.println("No Starting Position found.");
        return null;
    }



    public void draw( Graphics g) {
        for (int row = 0; row < mazeArray.length; row ++) {
            for (int col = 0; col < mazeArray[row].length; col ++) {
                int value = mazeArray[row][col];

                switch (value) {
                    case 0 -> g.setColor(Color.BLACK); // wall
                    case 1 -> g.setColor(Color.WHITE); // path
                    case 2 -> g.setColor(Color.RED); // end
                    case 3 -> g.setColor(Color.GREEN); // start
                    case 4 -> g.setColor(Color.BLUE); // path taken
                }

                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                //border
                g.setColor(Color.GRAY);
                g.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }

    }
}
