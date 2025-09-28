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
        System.out.println("MergeSort: " + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "MergeSort");

        copy = arr.clone();
        m.reset();
        t0 = System.currentTimeMillis();
        QuickSort.sort(copy, m);
        t1 = System.currentTimeMillis();
        System.out.println("QuickSort: " + (t1-t0) + " ms, comps=" + m.comparisons + ", depth=" + m.maxDepth);
        m.writeCSV("metrics.csv", "QuickSort");

        copy = arr.clone();
        m.reset();
        t0 = System.currentTimeMillis();
        int median = DeterministicSelect.select(copy, copy.length/2, m);
        t1 = System.currentTimeMillis();
        System.out.println("Select median: " + median + ", time=" + (t1-t0) + " ms");
        m.writeCSV("metrics.csv", "Select");

        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble(), rnd.nextDouble());
        t0 = System.currentTimeMillis();
        double d = ClosestPair.closest(pts);
        t1 = System.currentTimeMillis();
        System.out.println("Closest pair distance: " + d + ", time=" + (t1-t0) + " ms");
    }
}

