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
        move(speed);
        checkEnemyCollision();
        checkWorldBorder();
    }
    
    private void checkEnemyCollision(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);

        if (enemy == null){
            return;
        }

        enemy.takeDamage(damage);

        if (getWorld() != null){
            getWorld().removeObject(this);
        }
    }
    
    private void checkWorldBorder(){
        if (getWorld() == null){
            return;
        }

        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}

