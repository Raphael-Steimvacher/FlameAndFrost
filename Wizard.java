import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

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
    private final String attackKey;
    
    private static final int ATACK_COOLDOWN_TIME = 15;
    
    private int attackCooldown = 0;
    private boolean attackInputEnabled = false;
        
    public Wizard(
        int health, 
        int speed, 
        int damage,
        String upKey,
        String downKey,
        String leftKey,
        String rightKey,
        String attackKey,
        String imageName
    ){
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
        
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.attackKey = attackKey;
        
        GreenfootImage image = new GreenfootImage(imageName);
        image.scale(120, 120);
        setImage(image);
    }
    
    @Override
    public void act(){
        if(getWorld() == null){
            return;
        }
        
        moveWizard();
        
        if(attackCooldown > 0){
            attackCooldown--;
        }
        
        attack();
    }
    
    private boolean isControlKeyDown(
        String configuredKey, 
        String firstSoloKey, 
        String secondSoloKey,
        boolean singlePlayer
    ){
        if(singlePlayer){
            return Greenfoot.isKeyDown(firstSoloKey) || Greenfoot.isKeyDown(secondSoloKey);
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
    
        if(isControlKeyDown(upKey, "w", "up", singlePlayer)){
            dy = -1;
        }
    
        if(isControlKeyDown(downKey, "s", "down", singlePlayer)){
            dy = 1;
        }
    
        if(isControlKeyDown(rightKey, "d", "right", singlePlayer)){
            dx = 1;
        }
    
        if(isControlKeyDown(leftKey, "a", "left", singlePlayer)){
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
    
    private boolean isAttackKeyDown(){
        World world = getWorld();
        
        boolean singledPlayer = world instanceof Arena && ((Arena) world).isSinglePlayer();
        
        boolean pressed = isControlKeyDown(attackKey, "space", "enter", singledPlayer);
        
        if(!attackInputEnabled){
            if(!pressed){
                attackInputEnabled = true;
            }
            
            return false;
        }
        
        return pressed;
    }
    
    private void attack(){
        if(!isAttackKeyDown() || attackCooldown > 0){
            return;
        }
        
        int direction = calculateAttackDirection();
        
        PlayerProjectile projectile = createProjectile(direction, getDamage());
        
        getWorld().addObject(projectile, getX(), getY());
        
        attackCooldown = ATACK_COOLDOWN_TIME;
    }
    
    private int calculateAttackDirection(){
        double radians = Math.atan2(lastDirectionY, lastDirectionX);
        
        return (int) Math.toDegrees(radians);
    }
    
    protected abstract PlayerProjectile createProjectile(int direction, int damage);
    
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
    
   private void die() {
        World world = getWorld();
    
        if (world == null) {
            return;
        }
    
        world.removeObject(this);
    
        if (world instanceof Arena) {
            Arena arena = (Arena) world;
    
            if (arena.getObjects(Wizard.class).isEmpty()) {
                arena.gameOver();
            }
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
