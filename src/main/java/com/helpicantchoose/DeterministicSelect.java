package com.helpicantchoose;

public class DeterministicSelect {
    public static int select(int[] a, int k, Metrics m) {
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) return a[lo];
            m.enterRecursion();
            int pivot = medianOfMedians(a, lo, hi, m);
            int[] bounds = partition(a, lo, hi, pivot, m);
            m.exitRecursion();
            int lt = bounds[0], gt = bounds[1];
            if (k < lt) hi = lt - 1;
            else if (k > gt) lo = gt + 1;
            else return pivot;
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n < 5) {
            insertionSort(a, lo, hi, m);
            return a[lo + n/2];
        }
        m.enterRecursion();
        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            insertionSort(a, i, subHi, m);
            int medianIdx = i + (subHi - i) / 2;
            int tmp = a[lo + numMedians];
            a[lo + numMedians] = a[medianIdx];
            a[medianIdx] = tmp;
            numMedians++;
        }

        int res = medianOfMedians(a, lo, lo + numMedians - 1, m);
        m.exitRecursion();
        return res;
    }

    private static int[] partition(int[] a, int lo, int hi, int pivot, Metrics m) {
        int lt = lo, gt = hi;
        int i = lo;
        while (i <= gt) {
            m.countComparison();
            if (a[i] < pivot) {
                int tmp = a[lt]; a[lt] = a[i]; a[i] = tmp;
                lt++; i++;
            } else if (a[i] > pivot) {
                int tmp = a[gt]; a[gt] = a[i]; a[i] = tmp;
                gt--;
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
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
