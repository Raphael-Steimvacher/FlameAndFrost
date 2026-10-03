import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class EnemyProjectile extends Projectile{
    
    public EnemyProjectile(int damage, int speed, int direction){
        super(damage, speed, direction);
    }
    
    protected void checkCollision(){
        Wizard wizard = (Wizard) getOneIntersectingObject(Wizard.class);

        if (wizard == null){
            return;
        }

        wizard.takeDamage(getDamage());

        if (getWorld() != null){
            getWorld().removeObject(this);
        }
    }
}
