import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ArrowProjectile extends EnemyProjectile{
    
    public ArrowProjectile(int direction, int damage){
        super(damage, 7, direction);
        
        GreenfootImage image = new GreenfootImage("arrow-projectile.png");
    
        image.scale(64, 26);
        setImage(image);
    }
}
