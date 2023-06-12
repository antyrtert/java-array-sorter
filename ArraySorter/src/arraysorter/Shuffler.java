package arraysorter;

import java.util.Random;

/**
 * Shuffling algorithm
 * @author Voronin Daniil
 */
public class Shuffler extends ISorter {
    Random rnd = new Random();

    public Shuffler(Array array) {
        super(array);
    }

    @Override
    public void sort() {
        for (int i = 0; i < array.getLength(); i++) {
            array.swap(i, rnd.nextInt(array.getLength()));
        }
    }
}
