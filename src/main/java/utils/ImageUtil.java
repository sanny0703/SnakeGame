package utils;

import javax.swing.*;

public enum ImageUtil {
    SNAKE_HEAD(new ImageIcon("src/main/resources/head.png")),
    SNAKE_TAIL(new ImageIcon("src/main/resources/dot.png")),
    APPLE(new ImageIcon("src/main/resources/apple.png"));
    private ImageIcon imageIcon;
    ImageUtil(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }
    public ImageIcon getImageIcon(){
        return  this.imageIcon;
    }
}
