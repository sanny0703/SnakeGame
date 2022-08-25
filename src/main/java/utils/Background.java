package utils;

import java.awt.*;

public enum Background {
    BLACK(Color.BLACK),
    WHITE(Color.WHITE);
    private Color color;
    Background(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
