import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Skeleton extends Enemy{
    public Skeleton(){
        super(40, 2, 15, 30);
        
        GreenfootImage imagem = getImage();
        imagem.scale(90, 110);
    }
    
    public void act(){
        super.act();
    }
}
