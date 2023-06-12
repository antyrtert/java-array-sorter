package arraysorter;

/**
 * Modified insertion sorting algorithm
 * @author Voronin Daniil
 */
public class FastInsertionSorter extends ISorter {
    public FastInsertionSorter(Array array) {
        super(array);
    }
    
    @Override
    public void sort() {
        do {
            fastInsertionSort();
        } while (!valid());
    }
    
    private int binarySearch(int index, int a, int b) {
        var val = array.get(index);
        
        while (a <= b) {
            int m = (a + b) / 2;
            var med = array.get(m);
            
            if (med == val) return m;
            else if (med < val) a = m + 1;
            else if (med > val) b = m - 1;
        }
        
        return a;
    }
    
    private void fastInsertionSort() {
        for (int i = 1; i < array.getLength(); i++) {
            int j = binarySearch(i, 0, i);

            array.shift(j, i);
        }
    }
}
