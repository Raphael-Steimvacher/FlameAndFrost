import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Golem extends HybridEnemy{
    
    public Golem(){
        super(300, 1, 25, 100, 100, 350, 90, 120);
    }
    
    public void act(){
        // Add your action code here.
    }
    
    @Override
    protected void rangedAttack(int direction){
        RockProjectile Rock = new RockProjectile(direction, getDamage());
        
        getWorld().addObject(rock, getX(), getY());
    }
}
