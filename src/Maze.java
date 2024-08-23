import java.awt.*;

public class Maze {
    private int[][] mazeArray;
    private int tileSize;
    private Position position;
    private Position startPosition;


    public Maze(int[][] mazeArray, int tileSize){
        this.mazeArray = mazeArray;
        this.tileSize = tileSize;
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

    public boolean isValid(int[][] mazeArray, int y, int x){
        return  y >= 0 &&
                y < mazeArray.length &&
                x >= 0 &&
                x < mazeArray[0].length;
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
                }

                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                //border
                g.setColor(Color.GRAY);
                g.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }

    }
}
