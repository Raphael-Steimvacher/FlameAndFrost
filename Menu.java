import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Menu extends World{
    public Menu(){    
        super(1600, 800, 1); 
        
        showText("Frost & Flame", getWidth() / 2, 250);
        
        showText("pressione ENTER para jogar", getWidth() / 2, 400);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Arena());
        }
    }
}
