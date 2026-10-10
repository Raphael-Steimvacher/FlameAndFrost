import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class IceWizard extends Wizard {
    public IceWizard() {
        super(
            10000, 4, 20,
            "up", "down", "left", "right",
            "enter", WizardType.ICE.getImageName()
        );
    }

    @Override
    protected PlayerProjectile createProjectile(int direction, int damage){
        return new IceBlast(direction, damage);
    }
}
