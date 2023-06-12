package arraysorter;

/**
 * Shell sorting algorithm
 * @author Voronin Daniil
 */
public class ShellSorter extends ISorter {
    public ShellSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            shellSort();
        } while (!valid());
    }
    
    private void shellSort() {
        for (int s = array.getLength() / 2; s > 0; s /= 2) {
            for (int i = s; i < array.getLength(); i++) {
                for (int j = i - s; j >= 0 && array.get(j) > array.get(j + s); j -= s) {
                    array.swap(j, j + s);
                }
            }
        }
    }
}
