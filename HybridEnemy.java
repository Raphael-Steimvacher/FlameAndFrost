import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class HybridEnemy extends Enemy{
    private int meleeAttackCooldown = 0;
    private int rangedAttackCooldown = 0;
    
    private int meleeRange;
    private int rangedRange;
    
    private int meleeCooldownTime;
    private int rangedCooldownTime;
    
    public HybridEnemy(
        int health,
        int speed,
        int damage,
        int scoreValue,
        int meleeRage,
        int rangedRange,
        int meleeCooldownTime,
        int rangedCooldownTime
    ){
        super(health, speed, damage, scoreValue);
        
        this.meleeRange = meleeRange;
        this.rangedRange = rangedRange;
        
        this.meleeCooldownTime = meleeCooldownTime;
        this.rangedCooldownTime = rangedCooldownTime;
    }
    
    @Override
    public void act(){
        Wizard wizard = getWizard();
        
        if(wizard == null){
            return;
        }
        
        moveTowardWizard(wizard);
        handleAttack(wizard);
        
        updateCooldowns();
    }
    
    private void moveTowardWizard(Wizard wizard){
        double distance = getDistanceFromWizard(wizard);
        
        if(distance <= meleeRange){
            return;
        }
        
        turnTowards(wizard.getX(), wizard.getY());
        
        move(getSpeed());
    }
    
    private void handleAttack(Wizard wizard){
        double distance = getDistanceFromWizard(wizard);
        
        if(distance <= meleeRange){
            tryMeleeAttack(wizard);
            return;
        }
        
        if(distance <= rangedRange){
            tryRangedAttack(wizard);
        }
    }
    
    private void tryMeleeAttack(Wizard wizard){
        if(meleeAttackCooldown > 0){
            return;
        }
        
        wizard.takeDamage(getDamage());
        
        meleeAttackCooldown = meleeCooldownTime;
    }
    
    private void tryRangedAttack(Wizard wizard){
        if(rangedAttackCooldown > 0){
            return;
        }
        
        int direction = calculateAttackDirection(wizard);
        
        rangedAttack(direction);
        
        rangedAttackCooldown = rangedCooldownTime; 
    }
    
    private void updateCooldowns(){
        if(meleeAttackCooldown > 0){
            meleeAttackCooldown--;
        }
        
        if(rangedAttackCooldown > 0){
            rangedAttackCooldown--;
        }
    }
    
    protected abstract void rangedAttack(int direction);
}
