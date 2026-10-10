import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Arena extends World{
    private static final int WAVES_PER_STAGE = 4;
    private static final int WAVE_DELAY = 120;
    private static final int STAGE_DELAY = 180;
    
    private int score = 0;
    private ArenaStage currentStage = ArenaStage.FOREST;

    private int currentWave = 0;
    private int waveDelay = 0;
    private boolean batteStarted = false;
    private boolean gamefineshed = false;
    
    
    
    public Arena(){    
        super(1600, 800, 1);
        setBackground(currentStage.getBackgroundImage());
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
        
        String waveText = currentWave == 0
            ? "Próxima onda: 1/" + WAVES_PER_STAGE
            : "Onda: " + currentWave + "/" + WAVES_PER_STAGE;
        showText(waveText, 500, 30);
        showText("Cenario: ", 800, 30);
    }

    public void addScore(int points){
        score += points;
    }

    private void manageWaves(){
        if(gamefineshed){
           return; 
        }
        
        if(!batteStarted){
            startNextWave();
            
            batteStarted = true;

            return;
        }
        
        if(waveDelay > 0){
            waveDelay--;
            
            if(waveDelay == 0){
                showText(null, getWidth() / 2, 100);
                startNextWave();
            }
            
            return;
        }

        if(!getObjects(Enemy.class).isEmpty()){
            return;
        }

        if(currentWave == WAVES_PER_STAGE){
            ArenaStage nextStage = currentStage.getNextStage();
            
            if(nextStage == null){
                winGame();
            } else {
                startNextStage(nextStage);
            }
            
            return;
        }

        removeObjects(getObjects(EnemyProjectile.class));
        waveDelay =WAVE_DELAY;
        showText("Preparando proxima onda...", getWidth() / 2, 100);
    }
    
    private void startNextStage(ArenaStage nextStage){
        removeObjects(getObjects(Projectile.class));
        removeObjects(getObjects(Item.class));
        removeObjects(getObjects(ExplosionEffect.class));
        
        currentStage = nextStage;
        currentWave = 0;
        setBackground(currentStage.getBackgroundImage());
        
        for(Wizard wizard : getObjects(Wizard.class)){
            wizard.setLocation(getWidth() / 2, getWidth() / 2);
        }
        
        waveDelay = STAGE_DELAY;
        showText(currentStage.getBackgroundImage() + " - preparando primeira onda", getWidth() / 2, 100);
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
                spawnSkeleton(2);
                spawnHealthItem();
                break;
            
            case 4:
                spawnEnemy(new Golem(currentStage.getGolemImage()));
                spawnHealthItem();
                break;
        }
    }

    private void spawnEnemy(Enemy enemy){
        int[] position = getRandomSpawnPosition();
        
        int halfWidth = enemy.getImage().getWidth() / 2;
        int halfHeight = enemy.getImage().getHeight() / 2;
        
        int x = Math.max(halfWidth, Math.min(getWidth() - halfWidth - 1, position[0]));
        int y = Math.max(halfHeight, Math.min(getHeight() - halfHeight - 1, position[1]));
        
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
    
    private void spawnHealthItem(){
        HealthItem healthItem = new HealthItem(30);
        addObject(healthItem, getWidth() / 2, getHeight() / 2);
    }
    
    // minX, maxX, minY, maxY;
    protected int[][] getSpawnZones(){
        return currentStage.getSpawnZones();
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
        if(gamefineshed){
            return;
        }
        
        gamefineshed = true;
        Greenfoot.setWorld(new FinalScreen(false, score));
    }
    
    private void winGame(){
        gamefineshed = true;
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
    
    /* ===========================================================================================
     * Daqui para baixo eu queria separar em um unico arquivo pq seria basicamente configs de enum
     * ===========================================================================================
     */
    
    public enum ArenaStage {
        FOREST(
            "Floresta",
            "arena-floresta-1600x800.png",
            "green-golem.png",
            new int[][] {
                {550, 950, 0, 130},
                {550, 1050, 700, 799},
                {220, 380, 460, 590},
                {1200, 1360, 400, 550}
            }
        ),
        DESERT(
            "Deserto",
            "arena-deserto-1600x800.png",
            "dust-golem.png",
            new int[][] {
                {700, 900, 180, 230},
                {650, 950, 600, 650},
                {350, 420, 350, 450},
                {1180, 1240, 350, 450}
            }
        ),
        LAVA(
            "Lava",
            "arena-vulcão-1600x800.png",
            "lava-golem.png",
            new int[][] {
                {700, 900, 180, 220},
                {700, 900, 580, 620},
                {500, 550, 350, 450},
                {1040, 1080, 350, 450}
            }
        );

        private final String displayName;
        private final String backgroundImage;
        private final String golemImage;
        private final int[][] spawnZones;
    
        ArenaStage(
            String displayName,
            String backgroundImage,
            String golemImage,
            int[][] spawnZones
        ){
            this.displayName = displayName;
            this.backgroundImage = backgroundImage;
            this.golemImage = golemImage;
            this.spawnZones = spawnZones;
        }

        public String getDisplayName() {
            return displayName;
        }
    
        public String getBackgroundImage() {
            return backgroundImage;
        }
    
        public String getGolemImage() {
            return golemImage;
        }
    
        // Each row: minX, maxX, minY, maxY.
        public int[][] getSpawnZones() {
            return spawnZones;
        }
    
        public ArenaStage getNextStage() {
            ArenaStage[] stages = values();
            int nextIndex = ordinal() + 1;
    
            return nextIndex < stages.length ? stages[nextIndex] : null;
        }
    }
}
