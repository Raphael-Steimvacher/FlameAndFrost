import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FireWizard extends Wizard
{
    private int attackCooldown = 0;
    
    public FireWizard(){
        super(100, 4, 20);

        GreenfootImage imagem = getImage();
        imagem.scale(120, 120);
        
    }

    public void act(){
        super.act();
        
        attack();
        
        if(attackCooldown > 0){
            attackCooldown--;
        }
    }
    
    private void attack(){
        if (!Greenfoot.isKeyDown("space")){
            return;
        }

        if (attackCooldown > 0){
            return;
        }

        int direction = calculateAttackDirection();

        Fireball fireball = new Fireball(direction, getDamage());

        getWorld().addObject(fireball, getX(),getY());

        attackCooldown = 15;
    }

    private int calculateAttackDirection()
    {
        int dx = getLastDirectionX();
        int dy = getLastDirectionY();

        double radians = Math.atan2(dy, dx);

        double degrees = Math.toDegrees(radians);

        return (int) degrees;
    }
}
