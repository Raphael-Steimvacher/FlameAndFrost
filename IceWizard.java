import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import greenfoot.*;

public class IceWizard extends Wizard {
    private int attackCooldown = 0;

    public IceWizard() {
        super(
            100,
            4,
            20,
            "up",
            "down",
            "left",
            "right"
        );

        GreenfootImage imagem = getImage();
        imagem.scale(120, 120);
    }

    @Override
    public void act() {
        super.act();

        attack();

        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }

    private void attack() {
        if (getWorld() == null) {
            return;
        }

        if (!Greenfoot.isKeyDown("enter")) {
            return;
        }

        if (attackCooldown > 0) {
            return;
        }

        int direction = calculateAttackDirection();

        IceBlast iceBlast = new IceBlast(
            direction,
            getDamage()
        );

        getWorld().addObject(
            iceBlast,
            getX(),
            getY()
        );

        attackCooldown = 15;
    }

    private int calculateAttackDirection() {
        int dx = getLastDirectionX();
        int dy = getLastDirectionY();

        double radians = Math.atan2(dy, dx);
        double degrees = Math.toDegrees(radians);

        return (int) degrees;
    }
}
