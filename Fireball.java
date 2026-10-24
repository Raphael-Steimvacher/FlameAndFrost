import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fireball extends PlayerProjectile{
    
    public Fireball(int direction, int damage){
        super(damage, 10, direction);
    
        GreenfootImage image = new GreenfootImage("fire-projectile.png");
        image.scale(48, 48);
        setImage(image);
    }
}
