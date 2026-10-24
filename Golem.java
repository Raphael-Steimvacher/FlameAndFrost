import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Golem extends HybridEnemy{
    private final String projectileImageName;
    
    public Golem(){
        this("green-golem.png", 300);
    }
    
    public Golem(String imageName){
        this(imageName, 300);
    }
    
    public Golem(String imageName, int health) {
        this(imageName, health, "rock-projectile.png");
    }

    public Golem(String imageName, int health, String projectileImageName) {
        super(
            health,
            1,
            25,
            100,
            100,
            350,
            90,
            120
        );

        this.projectileImageName = projectileImageName;

        GreenfootImage image = new GreenfootImage(imageName);

        image.scale(140, 140);
        setImage(image);
    }
    
    @Override
    protected void rangedAttack(int direction){
        RockProjectile rock = new RockProjectile(direction, getDamage(), projectileImageName);
        
        getWorld().addObject(rock, getX(), getY());
    }
}
