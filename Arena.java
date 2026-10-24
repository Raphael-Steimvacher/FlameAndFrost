import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Arena extends World{
    private static final int WAVES_PER_STAGE = 4;
    private static final int WAVE_DELAY = 120;
    private static final int STAGE_DELAY = 180;
    
    private int score = 0;
    private int timeBonusScore = 0;
    private int flawlessBonusScore = 0;
    private final WavePerformance wavePerformance = new WavePerformance();
    private ArenaStage currentStage = ArenaStage.FOREST;

    private int currentWave = 0;
    private int waveDelay = 0;
    private boolean batteStarted = false;
    private boolean gamefineshed = false;
    
    private final int playerCount;
    private final WizardType soloWizardType;
    
    public Arena(){
        this(1, WizardType.FIRE);
    }
    
    public Arena(int playerCount){
        this(playerCount, WizardType.FIRE);
    }
    
    public Arena(int playerCount, WizardType soloWizardType){    
        super(1600, 800, 1);
        
        if(playerCount != 1 && playerCount != 2){
            throw new IllegalArgumentException("A qauntidade de jogadores deve ser 1 ou 2");
        }
        
        if(soloWizardType == null){
            throw new IllegalArgumentException("Escolha um personagem");
        }
        
        this.playerCount = playerCount;
        this.soloWizardType = soloWizardType;
        
        setBackground(currentStage.getBackgroundImage());
        prepare();
    }

    public void act(){
        takePositionClick();
        manageWaves();
        updateHUD();
    }

    private void prepare(){
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        if(isSinglePlayer()){
            addObject(soloWizardType.createWizard(), centerX, centerY);
        } else {
            addObject(new FireWizard(), centerX, centerY);
            addObject(new IceWizard(), centerX, centerY);
        }
    
        positionPlayers();
    }
    
    private void positionPlayers(){
        List<Wizard> wizards = getObjects(Wizard.class);
        
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        if(wizards.size() == 1){
            wizards.get(0).setLocation(centerX, centerY);
            
            return;
        }
        
        for(Wizard wizard : wizards){
            int offsetX = wizard instanceof FireWizard
                ? -80
                : 80;
            
            wizard.setLocation(centerX + offsetX, centerY);
        }
    }
    
    private String getWizardHealth(Class<? extends Wizard> wizardClass){
        List<? extends Wizard> wizards = getObjects(wizardClass);
        
        return wizards.isEmpty()
            ? "Eliminado"
            : String.valueOf(wizards.get(0).getHealth());
    }

    private void updateHUD() {
        if (isSinglePlayer()) {
            showText(
                "P1 " + soloWizardType.getDisplayName()
                    + ": " + getWizardHealth(Wizard.class),
                180,
                30
            );
        } else {
            showText(
                "P1 Fogo: " + getWizardHealth(FireWizard.class),
                180,
                30
            );
    
            showText(
                "P2 Gelo: " + getWizardHealth(IceWizard.class),
                480,
                30
            );
        }
    
        showText("Pontos: " + score, 760, 30);
        
        String performanceText = wavePerformance.isActive()
            ? String.format(
                java.util.Locale.ROOT,
                "Tempo: %.1fs | Sem dano: %s",
                wavePerformance.getElapsedSeconds(),
                wavePerformance.isFlawless() ? "SIM" : "NÃO"
            )
            : "";
        
        showText(performanceText, getWidth() / 2, 65);
    
        String waveText = currentWave == 0
            ? "Próxima onda: 1/" + WAVES_PER_STAGE
            : "Onda: " + currentWave + "/" + WAVES_PER_STAGE;
    
        showText(waveText, 1000, 30);
    
        showText(
            "Cenário: " + currentStage.getDisplayName(),
            1320,
            30
        );
    }

    public void addScore(int points){
        score += points;
    }
    
    public void registerPlayerDamage(int amount) {
        if (!gamefineshed) {
            wavePerformance.registerDamage(amount);
        }
    }
    
    public void onEnemyDefeated(int points) {
        if (gamefineshed) {
            return;
        }
    
        addScore(points);
    
        if (getObjects(Enemy.class).isEmpty()) {
            completeWave();
        }
    }
    
    private void completeWave() {
        if (!wavePerformance.finish()) {
            return;
        }
    
        WaveScoreConfig config =
            currentStage.getWaveScoreConfig(currentWave);
    
        double seconds = wavePerformance.getElapsedSeconds();
    
        int timeBonus = config.getTimeBonus(seconds);
    
        int flawlessBonus = wavePerformance.isFlawless()
            ? config.getFlawlessBonus()
            : 0;
    
        timeBonusScore += timeBonus;
        flawlessBonusScore += flawlessBonus;
    
        addScore(timeBonus + flawlessBonus);
    
        removeObjects(getObjects(EnemyProjectile.class));
    
        showText(
            String.format(
                java.util.Locale.ROOT,
                "Onda %d: %.1fs | Tempo: +%d | Sem dano: +%d",
                currentWave,
                seconds,
                timeBonus,
                flawlessBonus
            ),
            getWidth() / 2,
            145
        );
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
        
        completeWave();

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
            wizard.setLocation(getWidth() / 2, getHeight() / 2);
        }
        
        waveDelay = STAGE_DELAY;
        showText(currentStage.getBackgroundImage() + " - preparando primeira onda", getWidth() / 2, 100);
    }

    private void startNextWave(){
        currentWave++;
        
        if(currentWave == WAVES_PER_STAGE){
            Golem golem = new Golem(
                currentStage.getGolemImage(),
                currentStage.getGolemHealth(),
                currentStage.getGolemProjectileImage()
            );
            
            spawnEnemy(golem);
        } else {
            WaveConfig wave = currentStage.getWaveConfig(currentWave);
            
            spawnSlimes(wave.getSlimeAmount());
            spawnGoblin(wave.getGoblinAmount());
            spawnSkeleton(wave.getSkeletonAmount());
        }
        
        spawnHealthItem();
        showText(null, getWidth() / 2, 145);
        
        wavePerformance.start();
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
        int health = currentStage.getSlimeHealth();
        
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Slime(health));
        }
    }
    
    private void spawnGoblin(int amount){
        int health = currentStage.getGoblinHealth();
        
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Goblin(health));
        }
    }
    
    private void spawnSkeleton(int amount){
        int health = currentStage.getSkeletonHealth();
        
        for (int i = 0; i < amount; i++){
            spawnEnemy(new Skeleton(health));
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
        
        wavePerformance.finish();
        
        Greenfoot.setWorld(new FinalScreen(false, score));
    }
    
    private void winGame(){
        gamefineshed = true;
        Greenfoot.setWorld(new FinalScreen(true, score));
    }
    
    public boolean isSinglePlayer(){
        return playerCount == 1;
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
    
    @Override
    public void started() {
        wavePerformance.resume();
    }
    
    @Override
    public void stopped() {
        wavePerformance.pause();
    }
}
