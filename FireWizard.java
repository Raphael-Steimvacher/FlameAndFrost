import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FireWizard extends Wizard { 
    public FireWizard(){
        super(10000, 4, 20, 
            "w", "s", "a", "d", 
            "space", 
            WizardType.FIRE.getImageName()
        );
    }

    @Override
    protected PlayerProjectile createProjectile(int direction, int damage){
        return new Fireball(direction, damage);
    }
}
