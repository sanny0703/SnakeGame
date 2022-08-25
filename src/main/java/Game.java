import model.Board;
import model.SettingsPanel;
import utils.Background;
import utils.Level;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game() {
        initUI();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

    private void initUI() {
//        add(new SettingsPanel(),BorderLayout.NORTH);
        add(new Board(Level.MEDIUM, Background.BLACK),BorderLayout.SOUTH);


        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
