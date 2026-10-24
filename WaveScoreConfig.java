import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public final class WaveScoreConfig {
    private final int flawlessBonus;
    private final TimeBonus[] timeBonuses;

    public WaveScoreConfig(int flawlessBonus, TimeBonus... timeBonuses) {
        if (flawlessBonus < 0 || timeBonuses == null) {
            throw new IllegalArgumentException(
                "Configuração de pontos inválida."
            );
        }

        this.flawlessBonus = flawlessBonus;
        this.timeBonuses = timeBonuses.clone();

        int previousLimit = 0;
        int previousPoints = Integer.MAX_VALUE;

        for (TimeBonus bonus : this.timeBonuses) {
            if (
                bonus == null
                || bonus.getMaxSeconds() <= previousLimit
                || bonus.getPoints() > previousPoints
            ) {
                throw new IllegalArgumentException(
                    "Ordene os tempos do menor para o maior "
                    + "e os pontos do maior para o menor."
                );
            }

            previousLimit = bonus.getMaxSeconds();
            previousPoints = bonus.getPoints();
        }
    }

    public int getFlawlessBonus() {
        return flawlessBonus;
    }

    public int getTimeBonus(double elapsedSeconds) {
        if (
            !Double.isFinite(elapsedSeconds)
            || elapsedSeconds < 0
        ) {
            throw new IllegalArgumentException(
                "Tempo decorrido inválido."
            );
        }

        for (TimeBonus bonus : timeBonuses) {
            if (elapsedSeconds <= bonus.getMaxSeconds()) {
                return bonus.getPoints();
            }
        }

        return 0;
    }
}
