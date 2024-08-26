import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel {
    private JFrame window;
    private MazeGenerator mazeGenerator;
    private MazePanel mazePanel;

    public MenuScreen(JFrame window) {
        this.window = window;
        this.setLayout(new BorderLayout());

        mazeGenerator = new MazeGenerator(12, 16);
        int[][] initialMaze = mazeGenerator.generateMaze();
        mazePanel = new MazePanel(initialMaze, 50);
        mazePanel.setPreferredSize(new Dimension(mazePanel.getMazeWidth(), mazePanel.getMazeHeight()));

        JScrollPane mazeScrollPane = new JScrollPane(mazePanel);
        this.add(mazeScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));

        JButton loadMazeButton = new JButton("Load a new maze");
        loadMazeButton.addActionListener(e -> loadNewMaze());
        buttonPanel.add(loadMazeButton);

        JButton runPathFinderButton = new JButton("Run PathFinder(Brute Force)");
        runPathFinderButton.addActionListener(e -> runPathFinder());
        buttonPanel.add(runPathFinderButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        window.add(this);
        window.pack();
        window.setSize(new Dimension(mazePanel.getMazeWidth() + 40, mazePanel.getMazeHeight() + buttonPanel.getPreferredSize().height + 80));
    }

    private void loadNewMaze() {
        int[][] newMaze = mazeGenerator.generateMaze();
        mazePanel.setMaze(newMaze);
    }

    private void runPathFinder() {
        int[][] mazeArray = mazePanel.getMaze();
        MazePath pathFinder = new MazePath(mazeArray, 50, mazePanel);
        Position startPosition = mazeGenerator.getStartPosition(mazeArray);
        pathFinder.findPath(startPosition);
        mazePanel.repaint();
    }
}
