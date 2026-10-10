import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public enum WizardType {
    FIRE("Fogo", "FireMage.png"),
    ICE("Gelo", "IceMage.png");

    private final String displayName;
    private final String imageName;

    WizardType(String displayName, String imageName) {
        this.displayName = displayName;
        this.imageName = imageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImageName() {
        return imageName;
    }

    public Wizard createWizard() {
        if (this == FIRE) {
            return new FireWizard();
        }

        return new IceWizard();
    }
}