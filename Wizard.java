import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Wizard extends Actor
{
    private int health;
    private int maxHealth;
    private int speed;
    private int damage;
    
    private int lastDirectionX = 1;
    private int lastDirectionY = 0;
        
    public Wizard(int health, int speed, int damage){
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
    }
    
    public void act(){
        moveWizard();
    }
    
    private void moveWizard(){
        int dx = 0;
        int dy = 0;
    
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
            dy = -1;
        }
    
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            dy = 1;
        }
    
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            dx = 1;
        }
    
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            dx = -1;
        }
        
        if(dx != 0 || dy != 0){
            lastDirectionX = dx;
            lastDirectionY = dy;
        }
    
        int newX = getX() + dx * speed;
        int newY = getY() + dy * speed;
    
        limitMovement(newX, newY);
    }
    
    private void limitMovement(int newX, int newY){
        int halfWidth = getImage().getWidth() / 2;
        int halfHeight = getImage().getHeight() / 2;
        int arenaWidth = 300;
        int arenaHeight = 300;
    
        int minX = arenaWidth;
        int maxX = getWorld().getWidth() - arenaWidth;
    
        int minY = halfHeight;
        int maxY = getWorld().getHeight() - halfHeight;
    
        newX = Math.max(minX, Math.min(maxX, newX));
        newY = Math.max(minY, Math.min(maxY, newY));
    
        setLocation(newX, newY);
    }
    
    public void takeDamage(int amount){
        health -= amount;
        
        if(health <= 0){
            die();
        }
    }
    
    public void heal(int amount){
        health += amount;
        
        if(health > maxHealth){
            health = maxHealth;
        }
    }
    
    private void die(){
        World world = getWorld();
        
        if(world instanceof Arena){
            Arena arena = (Arena) world;
            
            arena.gameOver();
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }

    public int getDamage(){
        return damage;
    }
    
     public int getLastDirectionX(){
        return lastDirectionX;
    }

    public int getLastDirectionY(){
        return lastDirectionY;
    }
}
