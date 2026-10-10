import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Slime extends MeleeEnemy{
    public Slime(){
        this(30);
    }
    
    public Slime(int health){
        super(health, 1, 0, 10);

        GreenfootImage imagem = getImage();
        imagem.scale(70, 70);
    }
}
