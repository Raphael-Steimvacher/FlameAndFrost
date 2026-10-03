import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class MeleeEnemy extends Enemy{
    
    private int attackCooldown = 0;
    
    public MeleeEnemy(int health, int speed, int damage, int scoreValue){
        super(health, speed, damage, scoreValue);
    }
    
    @Override
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

        move(getSpeed());
    }
    
    private void attackWizard(){
        Wizard wizard = (Wizard) getOneIntersectingObject(Wizard.class);
        
        if(wizard == null){
            return;
        }
        
        if(attackCooldown > 0){
            return;
        }
        
        wizard.takeDamage(getDamage());
        
        attackCooldown = 30;
    }
}
