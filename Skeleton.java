import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Skeleton extends RangedEnemy{
    public Skeleton(){
        super(40, 2, 15, 30,300, 60);
        
        GreenfootImage imagem = getImage();
        imagem.scale(90, 110);
    }
    
    @Override
    protected void attack(int direction){
        ArrowProjectile arrow = new ArrowProjectile(direction, getDamage());
        
        getWorld().addObject(arrow, getX(), getY());
    }
}
