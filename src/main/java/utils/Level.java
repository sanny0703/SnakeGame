package utils;

public enum Level {
    EASY(180),
    MEDIUM(140),
    HARD(100);
    private  int delay;
     Level(int delay){
        this.delay = delay;
    }
    public int getDelay(){
        return delay;
    }


}
