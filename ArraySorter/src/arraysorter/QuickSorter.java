package arraysorter;


/**
 * Quick(Hoare) sorting algorithm
 * @author Voronin Daniil
 */
public class QuickSorter extends ISorter {
    public QuickSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            quickSort(0, array.getLength() - 1);
        } while (!valid());
    }
    
    private void quickSort(int left, int right) {
        if (left >= right) return;
        
        int l = left, r = right, m = (l + r) / 2;
        
        while (l <= r) {
            while (array.get(l) < array.get(m)) l++;
            while (array.get(r) > array.get(m)) r--;
            
            if (l <= r) {
                if (l == m) m = r;
                else if (r == m) m = l;
                array.swap(l++, r--);
            }
        }
        
        quickSort(left, l - 1);
        quickSort(l, right);
    }
}
