package adapter;

import utils.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovementAdapter extends KeyAdapter {
    private Direction direction;
    public MovementAdapter(Direction direction){
        this.direction = direction;
    }
    public Direction getDirection(){
        return  direction;
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                if( direction != Direction.RIGHT) direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if(direction!= Direction.LEFT) direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_UP:
                if(direction != Direction.DOWN) direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if(direction!= Direction.UP) direction = Direction.DOWN;
                break;
        }
    }
}
