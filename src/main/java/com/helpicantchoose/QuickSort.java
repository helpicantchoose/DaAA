package com.helpicantchoose;

import java.util.Random;

public class QuickSort {
    private static final Random rng = new Random();
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            if (hi - lo + 1 <= CUTOFF) {
                insertionSort(a, lo, hi, m);
                return;
            }
            m.enterRecursion();
            int p = partition(a, lo, hi, m);
            // recurse on smaller side
            if (p - lo < hi - p) {
                quicksort(a, lo, p - 1, m);
                lo = p + 1;
            } else {
                quicksort(a, p + 1, hi, m);
                hi = p - 1;
            }
            m.exitRecursion();
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[lo + rng.nextInt(hi - lo + 1)];
        int i = lo, j = hi;
        while (i <= j) {
            while (a[i] < pivot) { m.countComparison(); i++; }
            while (a[j] > pivot) { m.countComparison(); j--; }
            if (i <= j) {
                int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                i++; j--;
            }
        }
        return j;
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                m.countComparison();
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
}
