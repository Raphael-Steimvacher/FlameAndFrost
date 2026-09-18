import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class HealthItem extends Item
{
    int healAmount;
    
    public HealthItem(int healAmount){
        this.healAmount = healAmount;
    }
    
    public void act(){
        checkWizardCollision();
    }
    
    private void checkWizardCollision(){
        Wizard wizard = (Wizard) getOneIntersectingObject(Wizard.class);
        
        if(wizard != null){
            wizard.heal(healAmount);
            
            getWorld().removeObject(this);
        }
    }
}
