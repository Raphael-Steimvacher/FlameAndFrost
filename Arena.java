import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Arena extends World{
    private int score = 0;

    private int currentWave = 0;
    private int waveDelay = 0;
    private boolean batteStarted = false;

    public Arena(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 800, 1);
        prepare();
    }

    public void act(){
        updateHUD();

        takePositionClick();

        manageWaves();
    }

    private void prepare(){
        FireWizard wizard = new FireWizard();
        addObject(wizard, getWidth() / 2, getHeight() / 2);
    }

    private void updateHUD(){
        List<Wizard> wizards = getObjects(Wizard.class);

        if(!wizards.isEmpty()){
            Wizard wizard = wizards.get(0);

            showText("Vida: " + wizard.getHealth(), 100, 30);
        }

        showText("Pontos: " + score , 300, 30);

        showText("Onda: " + currentWave, 500, 30);
    }

    public void addScore(int points){
        score += points;
    }

    private void manageWaves(){
        if(!batteStarted){
            startNextWave();

            batteStarted = true;

            return;
        }

        if(!getObjects(Enemy.class).isEmpty()){
            return;
        }

        if(currentWave > 3){
            winGame();

            return;
        }

        if(waveDelay <= 0){
            waveDelay = 120;
        }

        waveDelay--;

        if(waveDelay == 0){
            startNextWave();
        }
    }

    private void spawnHealthItem(){
        HealthItem healthItem = new HealthItem(30);
        addObject(healthItem, getWidth() / 2, getHeight() / 2);
    }

    private void startNextWave(){
        currentWave++;

        switch(currentWave){
            case 1:
                spawnSlimes(5);
                spawnHealthItem();
                break;

            case 2:
                spawnSlimes(5);
                spawnGoblin(2);
                spawnHealthItem();
                break;

            case 3:
                spawnSlimes(3);
                spawnGoblin(4);
                spawnHealthItem();
                break;
        }
    }

    private void spawnEnemy(Enemy enemy){
        int[] position = getRandomSpawnPosition();
        
        int x = position[0];
        int y = position[1];
        
        addObject(enemy, x, y);
    }

    private void spawnSlimes(int amount){
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Slime());
        }
    }
    
    private void spawnGoblin(int amount){
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Goblin());
        }
    }
    
    private void spawnSkeleton(int amount){
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Skeleton());
        }
    }
    
    // minX, maxX, minY, maxY;
    protected int[][] getSpawnZones(){
        return new int[][] {
            // Superior
            {550, 950, 0, 130},
    
            // Inferior
            {550, 1050, 700, 800},
    
            // Esquerda
            {220, 380, 460, 590},
    
            // Direita
            {1200, 1360, 400, 550}
        };
    }
    
    private int[] getRandomSpawnPosition(){
        int[][] zones = getSpawnZones();
        
        int randomZoneIndex = Greenfoot.getRandomNumber(zones.length);
        
        int[] zone = zones[randomZoneIndex];
        
        int x = randomBetween(zone[0], zone[1]);
        int y = randomBetween(zone[2], zone[3]);
        
        return new int[] {x, y};
    }

    private int randomBetween(int min, int max){
        return Greenfoot.getRandomNumber(max - min + 1) + min; 
    }
    
    public void gameOver(){
        Greenfoot.setWorld(new FinalScreen(false, score));
    }
    
    private void winGame(){
        Greenfoot.setWorld(new FinalScreen(true, score));
    }

    private void takePositionClick(){
        if(Greenfoot.mouseClicked(null)){
            MouseInfo mouse = Greenfoot.getMouseInfo();

            if (mouse != null) {
                int x = mouse.getX();
                int y = mouse.getY();

                showText("X: " + x + " | Y: " + y, 300, 100);
            }
        }
    }
}
