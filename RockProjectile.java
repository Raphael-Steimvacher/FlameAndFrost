import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RockProjectile extends EnemyProjectile{
    public RockProjectile(int direction, int damage){
        super(damage, 5, direction);
        
        GreenfootImage image = new GreenfootImage(28, 28);
        image.setColor(new Color(110, 100, 90));
        image.fillOval(0, 0, 28, 28);
        setImage(image);
    }
}
