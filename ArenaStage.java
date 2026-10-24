import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/* Lembrando:
    *      Superior
    *      Inferior
    *      Esquerda
    *      Direita
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
        },
        
        30,  // Vida do Slime.
        50,  // Vida do Goblin.
        40,  // Vida do Skeleton.
        300, // Vida do Golem.

        new WaveConfig[] {
            new WaveConfig(5, 0, 0), // Onda 1.
            new WaveConfig(5, 2, 0), // Onda 2.
            new WaveConfig(3, 4, 2)  // Onda 3.
        },
        
        new WaveScoreConfig[] {
            // Onda 1.
            new WaveScoreConfig(
                100,
                new TimeBonus(15, 150),
                new TimeBonus(30, 100),
                new TimeBonus(45, 50)
            ),

            // Onda 2.
            new WaveScoreConfig(
                150,
                new TimeBonus(25, 200),
                new TimeBonus(45, 130),
                new TimeBonus(65, 60)
            ),
    
            // Onda 3.
            new WaveScoreConfig(
                200,
                new TimeBonus(40, 300),
                new TimeBonus(60, 200),
                new TimeBonus(90, 100)
            ),
    
            // Onda 4: chefe.
            new WaveScoreConfig(
                300,
                new TimeBonus(30, 500),
                new TimeBonus(50, 300),
                new TimeBonus(75, 150)
            )
        }
    ),
    DESERT(
        "Deserto",
        "arena-deserto-1600x800.png",
        "dust-golem.png",
        new int[][] {
            {650, 900, 115, 190},
            {535, 1080, 660, 750},
            {230, 390, 260, 360},
            {1150,1330, 480, 600}
        },
        
        45,  // Vida do Slime.
        75,  // Vida do Goblin.
        60,  // Vida do Skeleton.
        450, // Vida do Golem.

        new WaveConfig[] {
            new WaveConfig(8, 0, 0), // Onda 1.
            new WaveConfig(6, 3, 1), // Onda 2.
            new WaveConfig(5, 5, 3)  // Onda 3.
        },
        
        new WaveScoreConfig[] {
            // Onda 1.
            new WaveScoreConfig(
                150,
                new TimeBonus(25, 225),
                new TimeBonus(45, 150),
                new TimeBonus(65, 75)
            ),
        
            // Onda 2.
            new WaveScoreConfig(
                225,
                new TimeBonus(40, 300),
                new TimeBonus(65, 200),
                new TimeBonus(90, 100)
            ),
        
            // Onda 3.
            new WaveScoreConfig(
                300,
                new TimeBonus(55, 450),
                new TimeBonus(85, 300),
                new TimeBonus(120, 150)
            ),
        
            // Onda 4: chefe.
            new WaveScoreConfig(
                450,
                new TimeBonus(45, 750),
                new TimeBonus(70, 450),
                new TimeBonus(100, 225)
            )
        }
    ),
    LAVA(
        "Lava",
        "arena-vulcão-1600x800.png",
        "lava-golem.png",
        new int[][] {
            {630, 920, 100, 150},
            {610, 970, 560, 660},
            {390, 420, 300, 400},
            {1100, 1150, 290, 425}
        },
        
        60,  // Vida do Slime.
        100,  // Vida do Goblin.
        80,  // Vida do Skeleton.
        600, // Vida do Golem.

        new WaveConfig[] {
            new WaveConfig(0, 7, 0),
            new WaveConfig(0, 8, 2),
            new WaveConfig(0, 10, 4)
        },
        
        new WaveScoreConfig[] {
            // Onda 1.
            new WaveScoreConfig(
                250,
                new TimeBonus(35, 375),
                new TimeBonus(55, 250),
                new TimeBonus(80, 125)
            ),
        
            // Onda 2.
            new WaveScoreConfig(
                350,
                new TimeBonus(50, 500),
                new TimeBonus(80, 325),
                new TimeBonus(110, 150)
            ),
        
            // Onda 3.
            new WaveScoreConfig(
                500,
                new TimeBonus(70, 700),
                new TimeBonus(105, 450),
                new TimeBonus(145, 225)
            ),
        
            // Onda 4: chefe.
            new WaveScoreConfig(
                750,
                new TimeBonus(60, 1200),
                new TimeBonus(90, 750),
                new TimeBonus(130, 350)
            )
        }  
    );

    private final String displayName;
    private final String backgroundImage;
    private final String golemImage;
    private final int[][] spawnZones;
    
    private final int slimeHealth;
    private final int goblinHealth;
    private final int skeletonHealth;
    private final int golemHealth;
    
    private final WaveConfig[] waves;
    private final WaveScoreConfig[] waveScores;
    
    ArenaStage(
        String displayName,
        String backgroundImage,
        String golemImage,
        int[][] spawnZones,
        int slimeHealth,
        int goblinHealth,
        int skeletonHealth,
        int golemHealth,
        WaveConfig[] waves,
        WaveScoreConfig[] waveScores
    ){
        this.displayName = displayName;
        this.backgroundImage = backgroundImage;
        this.golemImage = golemImage;
        this.spawnZones = spawnZones;
        
        this.slimeHealth = slimeHealth;
        this.goblinHealth = goblinHealth;
        this.skeletonHealth = skeletonHealth;
        this.golemHealth = golemHealth;
        
        if (waveScores.length != waves.length + 1) {
            throw new IllegalArgumentException(
                "Configure pontos para as ondas normais e para o chefe."
            );
        }
    
        this.waves = waves;
        this.waveScores = waveScores.clone();
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
    
    public int getSlimeHealth() {
        return slimeHealth;
    }

    public int getGoblinHealth() {
        return goblinHealth;
    }
    
    public int getSkeletonHealth() {
        return skeletonHealth;
    }
    
    public int getGolemHealth() {
        return golemHealth;
    }
    
    public WaveConfig getWaveConfig(int waveNumber) {
        if (waveNumber < 1 || waveNumber > waves.length) {
            throw new IllegalArgumentException(
                "Onda normal inválida: " + waveNumber
            );
        }
    
        return waves[waveNumber - 1];
    }
    
    public ArenaStage getNextStage() {
        ArenaStage[] stages = values();
        int nextIndex = ordinal() + 1;
    
        return nextIndex < stages.length ? stages[nextIndex] : null;
    }
    
    public WaveScoreConfig getWaveScoreConfig(int waveNumber) {
        if (waveNumber < 1 || waveNumber > waveScores.length) {
            throw new IllegalArgumentException("Onda inválida: " + waveNumber);
        }
    
        return waveScores[waveNumber - 1];
    }
}