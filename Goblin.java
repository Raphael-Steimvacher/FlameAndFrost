import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Goblin extends Enemy{
    public Goblin(){
        super(50, 2, 10, 20);
        
        GreenfootImage imagem = getImage();
        imagem.scale(90, 90);
    }
}
