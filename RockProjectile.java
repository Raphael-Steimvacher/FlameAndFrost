import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RockProjectile extends EnemyProjectile{
     public RockProjectile(int direction, int damage) {
        this(direction, damage, "rock-projectile.png");
    }

    public RockProjectile(int direction, int damage, String imageName) {
        super(damage, 5, direction);

        GreenfootImage image = new GreenfootImage(imageName);

        image.scale(32, 32);
        setImage(image);
    }
}
