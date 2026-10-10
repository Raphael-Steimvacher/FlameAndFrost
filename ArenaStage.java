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
            new WaveConfig(0, 7, 0), // Onda 1.
            new WaveConfig(0, 8, 2), // Onda 2.
            new WaveConfig(0, 10, 4)  // Onda 3.
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
    
    ArenaStage(
        String displayName,
        String backgroundImage,
        String golemImage,
        int[][] spawnZones,
        int slimeHealth,
        int goblinHealth,
        int skeletonHealth,
        int golemHealth,
        WaveConfig[] waves
    ){
        this.displayName = displayName;
        this.backgroundImage = backgroundImage;
        this.golemImage = golemImage;
        this.spawnZones = spawnZones;
        
        this.slimeHealth = slimeHealth;
        this.goblinHealth = goblinHealth;
        this.skeletonHealth = skeletonHealth;
        this.golemHealth = golemHealth;
    
        this.waves = waves;
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
}