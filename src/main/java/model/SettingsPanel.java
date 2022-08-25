package model;

import utils.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class SettingsPanel extends JPanel implements ActionListener,Listener {
    private final Set<Listener> listeners = new HashSet<>();
    private JComboBox<Level> levelJComboBox;
    private JComboBox<Theme> themeJComboBox;
    private JLabel scoreBoardPanel;
    private GridBagConstraints gbc;

    public SettingsPanel() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 0;
        add(new JLabel("Start Game"), gbc);

        setLevelJComboBox();
        setThemeJComboBox();
        setScoreBoard();

    }
    private void  setScoreBoard(){
        scoreBoardPanel = new JLabel();
        gbc.weightx = 3;
        scoreBoardPanel.setVisible(true);
        scoreBoardPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        setScore(0);
        add(scoreBoardPanel,gbc);
    }
    private void  setScore(int score){
        scoreBoardPanel.setText("Your score: "+score);
    }
    private void setLevelJComboBox() {
        gbc.weightx = 1;
        levelJComboBox = new JComboBox<>(Level.values());
        levelJComboBox.addActionListener(this);
        levelJComboBox.setVisible(true);
        levelJComboBox.setBorder(BorderFactory.createLineBorder(Color.blue));
        add(levelJComboBox, gbc);
    }

    private void setThemeJComboBox() {
        gbc.weightx = 2;
        themeJComboBox = new JComboBox<>(Theme.values());
        themeJComboBox.addActionListener(this);
        themeJComboBox.setVisible(true);
        themeJComboBox.setBorder(BorderFactory.createLineBorder(Color.blue));
        add(themeJComboBox, gbc);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void fireEvent() {
        listeners.forEach(Listener::update);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Level currentLevel = (Level) levelJComboBox.getSelectedItem();
        Theme currentTheme = (Theme) themeJComboBox.getSelectedItem();
    }

    @Override
    public void update() {

    }
}
