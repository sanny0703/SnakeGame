package utils;

/**
 * class that defines the position of apple on the board
 */
public class ApplePosition  {

   private int positionX;
   private int positionY;
   public ApplePosition(int positionX,int positionY){
       this.positionX = positionX;
       this.positionY = positionY;
   }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "ApplePosition{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
