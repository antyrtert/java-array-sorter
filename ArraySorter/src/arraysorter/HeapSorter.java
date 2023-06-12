package arraysorter;

/**
 * Heap sorting algorithm
 * @author Voronin Daniil
 */
public class HeapSorter extends ISorter {
    public HeapSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            heapSort();
        } while (!valid());
    }
    
    private void heapSort() {
        for (int i = array.getLength() / 2 - 1; i >= 0; i--) {
            heap(array.getLength(), i);
        }
 
        for (int i = array.getLength() - 1; i > 0; i--) {
            array.swap(0, i);
 
            heap(i, 0);
        }
    }
    
    private void heap(int N, int i) {
        int largest = i,
            left = 2 * i + 1,
            right = 2 * (i + 1);
 
        if (left < N && array.get(left) > array.get(largest)) {
            largest = left;
        }
 
        if (right < N && array.get(right) > array.get(largest)) {
            largest = right;
        }
 
        if (largest != i) {
            array.swap(i, largest);
 
            heap(N, largest);
        }
    }
}
