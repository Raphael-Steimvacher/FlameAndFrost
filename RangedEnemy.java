import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class RangedEnemy extends Enemy{
    
    private int attackCooldown = 0;
    
    private int attackRange;
    private int cooldownTime;
    
    public RangedEnemy(
        int health, 
        int speed, 
        int damage, 
        int scoreValue, 
        int attackRange, 
        int cooldownTime
    ){
        super(health, speed, damage, scoreValue);
        this.attackCooldown = attackCooldown;
        this.cooldownTime = cooldownTime;
    }
    
    public void act(){
        Wizard wizard = getWizard();
        
        if(wizard == null){
            return;
        }
        
        moveTowardWizard(wizard); 
        attackWizard(wizard);
        
        if(attackCooldown > 0){
            attackCooldown--;
        }
    }
    
    private void moveTowardWizard(Wizard wizard){
        double distance = getDistanceFromWizard(wizard);
        
        if(distance <= attackRange){
            return;
        }
        
        turnTowards(wizard.getX(), wizard.getY());
        
        move(getSpeed());
    }
    
    private void attackWizard(Wizard wizard){
        double distance = getDistanceFromWizard(wizard);
        
        if(distance > attackRange){
            return;
        }
        
        if(attackCooldown > 0){
            return;
        }
        
        int direction = calculateAttackDirection(wizard);
        
        attack(direction);
        
        attackCooldown = cooldownTime;
    }
    
    protected abstract void attack(int direction);
}
