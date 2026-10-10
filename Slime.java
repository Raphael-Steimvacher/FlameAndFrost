import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Slime extends MeleeEnemy{
    public Slime(){
        super(30, 1, 0, 10);

        GreenfootImage imagem = getImage();
        imagem.scale(70, 70);
    }
}
