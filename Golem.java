import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Golem extends HybridEnemy{
    
    public Golem(){
        super(300, 1, 25, 100, 100, 350, 90, 120);
        
        GreenfootImage image = new GreenfootImage("green-golem.png");
        image.scale(140, 140);
        setImage(image);
    }
    
    @Override
    protected void rangedAttack(int direction){
        RockProjectile rock = new RockProjectile(direction, getDamage());
        
        getWorld().addObject(rock, getX(), getY());
    }
}
