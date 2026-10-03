import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public abstract class Enemy extends Actor
{
    private int health;
    private int speed;
    private int damage;
    private int scoreValue;

    private int attackCooldown = 0;

    public Enemy(int health, int speed, int damage, int scoreValue){
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.scoreValue = scoreValue;
    }

    public void act(){
        moveTowardWizard();
        attackWizard();

        if(attackCooldown > 0){
            attackCooldown--;
        }
    }

    private void moveTowardWizard(){
        Wizard wizard = getWizard();

        if(wizard == null){
            return;
        }

        turnTowards(wizard.getX(), wizard.getY());

        move(speed);
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
    
    private void attackWizard(){
        Wizard wizard = (Wizard) getOneIntersectingObject(Wizard.class);
        
        if(wizard == null){
            return;
        }
        
        if(attackCooldown > 0){
            return;
        }
        
        wizard.takeDamage(damage);
        
        attackCooldown = 30;
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
