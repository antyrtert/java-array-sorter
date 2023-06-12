package arraysorter;

import java.util.ArrayList;

/**
 * 
 * @author Voronin Daniil
 */
public class Event {
    public boolean supressInvoke = false;
    private final ArrayList<EventListener> listeners = new ArrayList<>();
    
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }
    
    public void invoke() {
        if (supressInvoke) return;
        listeners.forEach(EventListener::actionPerformed);
    }
    
    public interface EventListener {
        public void actionPerformed();
    }
    
    public void supressInvoke(Runnable action) {
        supressInvoke = true;
        
        action.run();
        
        supressInvoke = false;
        invoke();
    }
}
