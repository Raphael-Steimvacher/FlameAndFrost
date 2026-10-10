import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Wizard extends Actor
{
    private int health;
    private int maxHealth;
    private int speed;
    private int damage;
    
    private int lastDirectionX = 1;
    private int lastDirectionY = 0;
    
    private final String upKey;
    private final String downKey;
    private final String leftKey;
    private final String rightKey;
        
    public Wizard(
        int health, 
        int speed, 
        int damage,
        String upKey,
        String downKey,
        String leftKey,
        String rightKey
    ){
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
        
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }
    
    public void act(){
        moveWizard();
    }
    
    private boolean isMovementKeyDown(
        String configuredKey, 
        String wasdKey, 
        String arrowKey,
        boolean singlePlayer
    ){
        if(singlePlayer){
            return Greenfoot.isKeyDown(wasdKey) || Greenfoot.isKeyDown(arrowKey);
        }
        
        return Greenfoot.isKeyDown(configuredKey);
    }
    
    private void moveWizard(){
        World world = getWorld();
        
        if(world == null){
            return;
        }
        
        boolean singlePlayer = world instanceof Arena && ((Arena) world).isSinglePlayer();
        
        int dx = 0;
        int dy = 0;
    
        if(isMovementKeyDown(upKey, "W", "up", singlePlayer)){
            dy = -1;
        }
    
        if(isMovementKeyDown(downKey, "s", "down", singlePlayer)){
            dy = 1;
        }
    
        if(isMovementKeyDown(rightKey, "d", "right", singlePlayer)){
            dx = 1;
        }
    
        if(isMovementKeyDown(leftKey, "a", "left", singlePlayer)){
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
