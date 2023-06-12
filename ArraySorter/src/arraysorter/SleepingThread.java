package arraysorter;

/**
 *
 * @author Voronin Daniil
 */
public class SleepingThread {
    public boolean paused = false;
    private final Runnable action;
    private Thread thread;
    
    public SleepingThread(Runnable action) {
        thread = new Thread(this.action = action);
    }
    
    public boolean isRunning() {
        return thread.isAlive();
    }
    
    public void start() {
        if (thread.getState() == Thread.State.TERMINATED) {
            thread = new Thread(action);
        } else {
            stop();
        }
        thread.start();
    }
    
    public void stop() {
        if (thread.isAlive()) {
            paused = false;
            thread.interrupt();
        }
    }
    
    public void sleep(long ms) {
        do {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException ex) {
                System.err.print("Interrupt: ");
                // break thread
                int error = 0 / 0;
            }
        } while (paused);
    }
    
    public void interrupt() {
        thread.interrupt();
    }
    
    public void pause() {
        paused = true;
    }
    
    public void resume() {
        paused = false;
    }
    
    public void run() {
        thread.run();
    }
}
