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