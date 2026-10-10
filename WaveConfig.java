import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WaveConfig {
    private final int slimeAmount;
    private final int goblinAmount;
    private final int skeletonAmount;

    public WaveConfig(
        int slimeAmount,
        int goblinAmount,
        int skeletonAmount
    ) {
        this.slimeAmount = slimeAmount;
        this.goblinAmount = goblinAmount;
        this.skeletonAmount = skeletonAmount;
    }

    public int getSlimeAmount() {
        return slimeAmount;
    }

    public int getGoblinAmount() {
        return goblinAmount;
    }

    public int getSkeletonAmount() {
        return skeletonAmount;
    }
}
