package arraysorter;

import java.lang.reflect.*;
import java.util.logging.*;

/**
 * Algorithm switching enum
 * @author Voronin Daniil
 */
public enum Algorithm {
        Shuffle("Shuffle", Shuffler.class),
        Reverse("Reverse", Reverser.class),
        BubbleSort("Bubble sort", BubbleSorter.class),
        InsertionSort("Insertion sort", InsertionSorter.class),
        FastInsertionSort("Fast insertion sort", FastInsertionSorter.class),
        OddEvenSort("Odd even sort", OddEvenSorter.class),
        GnomeSort("Gnome sort", GnomeSorter.class),
        SelectionSort("Selection sort", SelectionSorter.class),
        ShellSort("Shell sort", ShellSorter.class),
        QuickSort("Quick sort", QuickSorter.class),
        MergeSort("Merge sort", MergeSorter.class),
        HeapSort("Heap sort", HeapSorter.class),
        BogoSort("Bogo sort", BogoSorter.class);
        
        private final String name;
        private final Class<ISorter> clss;
        
        Algorithm(String name, Class clss) {
            this.name = name;
            this.clss = clss;
        }
        
        public static Algorithm get(String name) {
            for (Algorithm algorithm : Algorithm.values()) {
                if (algorithm.name.equals(name)) {
                    return algorithm;
                }
            }
            return null;
        }
        
        public ISorter newInstance(Array array) {
            try {
                Constructor ctor = clss.getConstructor(Array.class);
                return (ISorter) ctor.newInstance(new Object[] { array });
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ArraySorter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        @Override
        public String toString() {
            return name;
        }
    }