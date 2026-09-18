import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Slime extends Enemy{
    public Slime(){
        super(30, 1, 5, 10);

        GreenfootImage imagem = getImage();
        imagem.scale(70, 70);
    }

    public void act(){
        super.act();
    }
}
