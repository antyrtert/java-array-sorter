package arraysorter;

/**
 * Merge sorting algorithm
 * @author Voronin Daniil
 */
public class MergeSorter extends ISorter {
    public MergeSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            mergeSort(0, array.getLength() - 1);
        } while (!valid());
    }
    
    public void mergeSort(int left, int right) {
        if (left >= right) return;
        
        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    public void merge(int left, int mid, int right) {
        int i = left - 1, j = mid + 1;

        while (++i <= mid && j <= right) {
            if (array.get(i) > array.get(j)) {
                array.shift(i, j);
                mid++;
                j++;
            }
        }
    }
}
