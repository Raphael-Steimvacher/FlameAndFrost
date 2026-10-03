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

        if(wizards.isEmpty()){
            return null;
        }

        return wizards.get(0);
    }

    public void takeDamage(int amount){
        health -= amount;

        if(health <= 0){
            die();
        }
    }

    private void die(){
        World world = getWorld();

        if(world instanceof Arena){
            Arena arena = (Arena) world;

            arena.addScore(scoreValue);
        }

        world.removeObject(this);
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
