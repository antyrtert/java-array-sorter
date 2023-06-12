package arraysorter;

/**
 * Gnome sorting algorithm
 * @author Voronin Daniil
 */
public class GnomeSorter extends ISorter {
    public GnomeSorter(Array array) {
        super(array);
    }
    
    @Override
    public void sort() {
        do {
            gnomeSort();
        } while (!valid());
    }
    
    private void gnomeSort() {
        int i = 0;
        while (++i < array.getLength()) {
            if (i > 0 && array.get(i) <= array.get(i - 1)) {
                array.swap(i--, i--);
            }
        }
    }
}
