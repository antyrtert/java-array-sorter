package arraysorter;

/**
 * Interface for other sorting classes
 * @author Voronin Daniil
 */
public abstract class ISorter {
    public final Array array ;
    
    public ISorter(Array array) {
        this.array = array;
    }
    
    public abstract void sort();
    
    public boolean valid() {
        var current = array.get(0);
        for (int i = 1; i < array.getLength(); i++) {
            var next = array.get(i);
            if (current > next) {
                return false;
            }
            current = next;
        }
        return true;
    }
}
