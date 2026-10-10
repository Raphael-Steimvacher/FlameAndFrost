import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Skeleton extends RangedEnemy{
    public Skeleton(){
        this(40);
    }
    
    public Skeleton(int health){
        super(health, 1, 0, 30, 300, 60);
        
        GreenfootImage imagem = getImage();
        imagem.scale(90, 110);
    }
    
    @Override
    protected void attack(int direction){
        ArrowProjectile arrow = new ArrowProjectile(direction, getDamage());
        
        getWorld().addObject(arrow, getX(), getY());
    }
}
