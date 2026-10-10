import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public abstract class Enemy extends Actor
{
    private int health;
    private int speed;
    private int damage;
    private int scoreValue;

    protected Enemy(int health, int speed, int damage, int scoreValue){
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.scoreValue = scoreValue;
    }

    protected Wizard getWizard(){
        if(getWorld() == null){
            return null;
        }

        List<Wizard> wizards = getWorld().getObjects(Wizard.class);
        
        Wizard nearestWizard = null;
        double nearestDistance = Double.MAX_VALUE;
        
        for(Wizard wizard : wizards){
            double distance = getDistanceFromWizard(wizard);
            
            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestWizard = wizard;
            }
        }

        return nearestWizard;
    }

    public void takeDamage(int amount){
        health -= amount;

        if(health <= 0){
            die();
        }
    }

    private void die() {
        World world = getWorld();
    
        if (world == null) {
            return;
        }
    
        if (world instanceof Arena) {
            ((Arena) world).addScore(scoreValue);
        }
    
        world.removeObject(this);
    }
    
    protected double getDistanceFromWizard(Wizard wizard){
        int dx = wizard.getX() - getX();
        int dy = wizard.getY() - getY();
        
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    protected    int calculateAttackDirection(Wizard wizard){
        int dx = wizard.getX() - getX();
        int dy = wizard.getY() - getY(); 
        
        double radians = Math.atan2(dy, dx);
        
        double degrees = Math.toDegrees(radians);
        
        return (int) degrees;
    }

    public int getHealth(){
        return health;
    }
    
    protected int getSpeed(){
        return speed;
    }
    
    protected int getDamage(){
        return damage;
    }
}
