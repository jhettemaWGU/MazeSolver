import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel {
    private JFrame window;
    private MazeGenerator mazeGenerator;
    private Panel panel;

    public MenuScreen(JFrame window) {
        this.window = window;
        this.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));

        JButton loadMazeButton = new JButton("Load a new maze");
        loadMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNewMaze();
            }
        });
        buttonPanel.add(loadMazeButton);

        JButton runPathFinderButton = new JButton("Run PathFinder(brute force)");
        runPathFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPathFinder();
            }
        });
        buttonPanel.add(runPathFinderButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        panel = new Panel();
        this.add(panel, BorderLayout.CENTER);
    }

    private void loadNewMaze() {
        mazeGenerator = new MazeGenerator(12, 16);
        int[][] newMaze = mazeGenerator.generateMaze();
        panel.setMaze(newMaze);
        panel.repaint();
    }

    private void runPathFinder() {
        if (mazeGenerator != null) {
            MazePath pathFinder = new MazePath(mazeGenerator.generateMaze(), 50, panel);
            Position startPosition = mazeGenerator.getStartPosition(panel.getMaze());
            pathFinder.findPath(startPosition);
        }
    }
}
