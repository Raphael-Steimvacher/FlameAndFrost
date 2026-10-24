import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.LongSupplier;

// Estado de uma unica onda. O relogio pode ser substituido nos testes.
public final class WavePerformance {
    private final LongSupplier clock;
    private long elapsedNanos;
    private long runningSince;
    private boolean active;
    private boolean running;
    private boolean flawless;

    public WavePerformance() {
        this(System::nanoTime);
    }

    WavePerformance(LongSupplier clock) {
        if (clock == null) {
            throw new IllegalArgumentException("Informe um relogio.");
        }
        this.clock = clock;
    }

    public void start() {
        elapsedNanos = 0;
        flawless = true;
        active = true;
        running = true;
        runningSince = clock.getAsLong();
    }

    public void registerDamage(int amount) {
        if (active && amount > 0) {
            flawless = false;
        }
    }

    public void pause() {
        if (active && running) {
            elapsedNanos += clock.getAsLong() - runningSince;
            running = false;
        }
    }

    public void resume() {
        if (active && !running) {
            runningSince = clock.getAsLong();
            running = true;
        }
    }

    // Retorna false quando a onda ja foi encerrada.
    public boolean finish() {
        if (!active) {
            return false;
        }
        pause();
        active = false;
        return true;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isFlawless() {
        return flawless;
    }

    public double getElapsedSeconds() {
        long total = elapsedNanos;
        if (active && running) {
            total += clock.getAsLong() - runningSince;
        }
        return total / 1_000_000_000.0;
    }
}
