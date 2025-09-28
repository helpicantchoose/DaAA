package com.helpicantchoose;

public class MergeSort {
    private static final int CUTOFF = 32;

    public static void sort(int[] a, Metrics m) {
        int[] aux = new int[a.length];
        m.addAlloc(a.length);
        sort(a, aux, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(a, lo, hi, m);
            return;
        }
        m.enterRecursion();
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid, m);
        sort(a, aux, mid + 1, hi, m);
        merge(a, aux, lo, mid, hi, m);
        m.exitRecursion();
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Metrics m) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else {
                m.countComparison();
                if (aux[i] <= aux[j]) a[k] = aux[i++];
                else a[k] = aux[j++];
            }
        }
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



