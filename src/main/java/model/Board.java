package model;

import adapter.MovementAdapter;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener,Listener {
    private final Level LEVEL;
    private final Background BACKGROUND_COLOR;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private final int DOT_SIZE = 10;


    // we don't want either 0 or 600 to be our apple position
    private final int RANDOM = 59;
    private final Timer TIMER;
    // (600*600)/(10*10)
    // total board area by one dot area
    private final int TOTAL_DOTS = 3600;
    // arrays to store the position of all the dots of the snake
    private final int[] position_x = new int[TOTAL_DOTS];
    private final int[] position_y = new int[TOTAL_DOTS];

    // variable to hold the direction of snake after every move
    private MovementAdapter directionAdapter;
    // variable to hold the current size of the snake
    private int snakeSize;
    // tells whether the game is over or not
    private boolean isGameMode;
    private ApplePosition applePosition;
    private  final JLabel label;

    public Board(Level level, Background background) {
        this.LEVEL = level;
        this.BACKGROUND_COLOR = background;
        TIMER = new Timer(LEVEL.getDelay(), this);
        label = new JLabel();

        init();
    }

    private void init() {
        // initial direction
        directionAdapter = new MovementAdapter(Direction.RIGHT);
        addKeyListener(directionAdapter);
        setBackground(BACKGROUND_COLOR.getColor());
        setFocusable(true);



        initSnake();
        setScore();
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setNewApplePosition();
        isGameMode = true;
        startGame();
    }
    private void  setScore(){
        label.setText("Your Score:"+(snakeSize-3));
    }

    private void initSnake() {
        snakeSize = 3;// initial snake size
        for (int i = 0; i < snakeSize; i++) {
            position_x[i] = 100 - i * DOT_SIZE;
            position_y[i] = 100;
        }
    }

    private void setNewApplePosition() {
        int rx = (int) (Math.random() * RANDOM);
        int x = rx * DOT_SIZE;
        int ry = (int) (Math.random() * RANDOM);
        int y = ry * DOT_SIZE;
        applePosition = new ApplePosition(x, y);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameMode) {
            g.drawImage(ImageUtil.APPLE.getImageIcon().getImage(), applePosition.getPositionX(), applePosition.getPositionY(), this);
            for (int i = 0; i < snakeSize; i++) {
                if (i == 0)
                    g.drawImage(ImageUtil.SNAKE_HEAD.getImageIcon().getImage(), position_x[i], position_y[i], this);
                else g.drawImage(ImageUtil.SNAKE_TAIL.getImageIcon().getImage(), position_x[i], position_y[i], this);
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            endGame(g);
            TIMER.stop();
        }


    }
    private void  startGame(){
        TIMER.start();
    }
    private  void  pauseGame(){
        TIMER.stop();
    }
    private void endGame(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameMode) {
            checkAppleEaten();
            moveSnake();
            checkCollision();
        }
        repaint();
    }

    private void checkAppleEaten() {
        if (position_x[0] == applePosition.getPositionX() && position_y[0] == applePosition.getPositionY()) {
            snakeSize++;
            setNewApplePosition();
            setScore();
        }

    }

    private void checkCollision() {
        for (int i = snakeSize; i > 0; i--) {
            if ((snakeSize > 4) && (position_x[0] == position_x[i]) && (position_y[0] == position_y[i])) {
                isGameMode = false;
                break;
            }
        }
        if (outOfBounds()) {
            isGameMode = false;
        }

    }

    private boolean outOfBounds() {
        int head_x = position_x[0], head_y = position_y[0];
        return (head_x >= BOARD_WIDTH || head_y >= BOARD_HEIGHT || head_x<0 || head_y <0);

    }

    private void moveSnake() {
        for (int i = snakeSize - 1; i > 0; i--) {
            position_x[i] = position_x[i - 1];
            position_y[i] = position_y[i - 1];
        }
        // now move head
        switch (directionAdapter.getDirection()) {
            case UP:
                position_y[0] -= DOT_SIZE;
                break;
            case DOWN:
                position_y[0] += DOT_SIZE;
                break;
            case LEFT:
                position_x[0] -= DOT_SIZE;
                break;
            case RIGHT:
                position_x[0] += DOT_SIZE;
                break;

        }
    }

    @Override
    public void update() {

    }
}
