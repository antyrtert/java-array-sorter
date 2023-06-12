package arraysorter;

/**
 * Odd-even sorting algorithm
 * @author Voronin Daniil
 */
public class OddEvenSorter extends ISorter {
    public OddEvenSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            oddEvenSort();
        } while (!valid());
    }
    
    public void oddEvenSort() {
        boolean sorted = false;
        int length = array.getLength();

        while (!sorted) {
            sorted = true;

            for (int i = 1; i < length - 1; i += 2) {
                if (array.get(i) > array.get(i + 1)) {
                    array.swap(i, i + 1);
                    sorted = false;
                }
            }

            for (int i = 0; i < length - 1; i += 2) {
                if (array.get(i) > array.get(i + 1)) {
                    array.swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }
}
