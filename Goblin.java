import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Goblin extends MeleeEnemy{
    public Goblin(){
        this(50);
    }
    
    public Goblin(int health){
        super(health, 2, 0, 20);
        
        GreenfootImage imagem = getImage();
        imagem.scale(90, 90);
    }
}
