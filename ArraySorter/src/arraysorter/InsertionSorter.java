package arraysorter;

/**
 * Insertion sorting algorithm
 * @author Voronin Daniil
 */
public class InsertionSorter extends ISorter {
    public InsertionSorter(Array array) {
        super(array);
    }
    
    @Override
    public void sort() {
        do {
            insertionSort();
        } while (!valid());
    }
    
    private void insertionSort() {
        for (int i = 1; i < array.getLength(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j - 1) < array.get(j)) break;
                array.swap(j - 1, j);
            }
        }
    }
}
