import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class PlayerProjectile extends Projectile{
    
    public PlayerProjectile(int damage, int speed, int direction){
        super(damage, speed, direction);
    }
    
    protected void checkCollision(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);

        if (enemy == null){
            return;
        }

        enemy.takeDamage(getDamage());

        if (getWorld() != null){
            getWorld().removeObject(this);
        }
    }
}
