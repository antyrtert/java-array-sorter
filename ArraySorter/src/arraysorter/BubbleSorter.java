package arraysorter;

/**
 * Bubble sorting algorithm
 * @author Voronin Daniil
 */
public class BubbleSorter extends ISorter {
    public BubbleSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            bubbleSort();
        } while (!valid());
    }
    
    private void bubbleSort() {
        for (int i = 0; i < array.getLength(); i++) {
            boolean sorted = true;
            for (int j = 1; j < array.getLength() - i; j++) {
                if (array.get(j) < array.get(j - 1)) {
                    array.swap(j, j - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}
