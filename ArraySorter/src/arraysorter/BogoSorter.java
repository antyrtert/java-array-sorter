package arraysorter;

import java.util.Random;

/**
 * Bogo sorting algorithm
 * @author Voronin Daniil
 */
public class BogoSorter extends ISorter {
    Random rnd = new Random();
    
    public BogoSorter(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        do {
            array.swap(rnd.nextInt(array.getLength()), rnd.nextInt(array.getLength()));
        } while (!valid());
    }
}
