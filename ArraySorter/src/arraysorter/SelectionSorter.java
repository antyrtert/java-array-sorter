package arraysorter;

/**
 * Selection sorting algorithm
 * @author Voronin Daniil
 */
public class SelectionSorter extends ISorter {
    public SelectionSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            selectionSort();
        } while (!valid());
    }
    
    private void selectionSort() {
        for (int i = 0; i < array.getLength(); i++) {
            int min = i;
            for (int j = i + 1; j < array.getLength(); j++) {
                if (array.get(j) < array.get(min)) {
                    min = j;
                }
            }
            array.swap(i, min);
        }
    }
}
