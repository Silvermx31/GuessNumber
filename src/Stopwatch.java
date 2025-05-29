/**
 * Mõõdab mängu aega
 */
public class Stopwatch {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    /**
     * Käivitab stopperi
     */
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    /**
     * Peatab stopperi ja summeerib aja
     */
    public void stop() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    /**
     * Tagastab vormindatud mänguaja kujul 00:00:00
     */
    public String getElapsedTime() {
        long totalMillis = getElapsedMillis();
        long seconds = totalMillis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    /**
     * Tagastab mänguaja millisekundites
     */
    public long getElapsedMillis() {
        if (running) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        } else {
            return elapsedTime;
        }
    }

    /**
     * Lähtestab stopperi nulli
     */
    public void reset() {
        startTime = 0;
        elapsedTime = 0;
        running = false;
    }
}
