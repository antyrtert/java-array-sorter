package arraysorter;

/**
 * Reversing algorithm
 * @author Voronin Daniil
 */
public class Reverser extends ISorter {

    public Reverser(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        int len = array.getLength() - 1;
        for (int i = 0; i <= len / 2; i++) {
            array.swap(i, len - i);
        }
    }
}
