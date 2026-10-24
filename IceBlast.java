import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class IceBlast extends PlayerProjectile{
    
    public IceBlast(int direction, int damage){
        super(damage, 8, direction);
        
    GreenfootImage image = new GreenfootImage("arrow-projectile.png");

    image.scale(64, 26);
    setImage(image);
    }
}
