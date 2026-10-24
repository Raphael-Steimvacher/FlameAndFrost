import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public final class TimeBonus {
    
    private final int maxSeconds;
    private final int points;
    
    public TimeBonus(int maxSeconds, int points){
        if(maxSeconds <= 0 || points < 0){
            throw new IllegalArgumentException("Tempo deve ser positivo e pontos nao negativos.");
        }
        
        this.maxSeconds = maxSeconds;
        this.points = points;
    }
    
    public int getMaxSeconds() {
        return maxSeconds;
    }

    public int getPoints() {
        return points;
    }
}
