package com.helpicantchoose;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Metrics m = new Metrics();
        Random rnd = new Random();

        int n = 10000;
        int[] arr = rnd.ints(n, 0, 100000).toArray();

        int[] copy = arr.clone();
        m.reset();
        long t0 = System.currentTimeMillis();
        MergeSort.sort(copy, m);
        long t1 = System.currentTimeMillis();
        long mergeTime = (t1-t0);
        System.out.println("MergeSort: " + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "MergeSort", mergeTime);

        copy = arr.clone();
        m.reset();
        t0 = System.currentTimeMillis();
        QuickSort.sort(copy, m);
        t1 = System.currentTimeMillis();
        long QuickTime = (t1-t0);
        System.out.println("QuickSort: " + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "QuickSort", QuickTime);

        copy = arr.clone();
        m.reset();
        t0 = System.currentTimeMillis();
        DeterministicSelect.select(copy, copy.length/2, m);
        t1 = System.currentTimeMillis();
        long DeterministicSelectTime = (t1-t0);
        System.out.println("Select median: " + " time=" + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "Select", DeterministicSelectTime);

        m.reset();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
        t0 = System.currentTimeMillis();ClosestPair.closest(pts, m);
        t1 = System.currentTimeMillis();
        long ClosestPairTime = (t1-t0);
        System.out.println("Closest pair distance: " + "time=" + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "ClosestPair", ClosestPairTime);
    }
}

