import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public abstract class Projectile extends Actor
{
    private int damage;
    private int speed;
    
    public Projectile(int damage, int speed, int direction){
        this.damage = damage;
        this.speed = speed;
        
        setRotation(direction);
    }
    
    public void act(){
        moveProjectile();
        checkEnemyCollision();
        checkWorldBorder();
    }
    
    private void moveProjectile(){
        move(speed);
    }
    
    protected abstract void checkEnemyCollision();
    
    private void checkWorldBorder(){
        if (getWorld() == null){
            return;
        }

        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }
    
    protected int getDamage(){
        return damage;
    }
}

