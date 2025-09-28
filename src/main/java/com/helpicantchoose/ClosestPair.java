package com.helpicantchoose;

import java.util.*;

public class ClosestPair {
    public static double closest(Point[] pts) {
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return rec(pts, 0, pts.length - 1);
    }

    private static double rec(Point[] pts, int lo, int hi) {
        if (hi - lo <= 3) return brute(pts, lo, hi);
        int mid = (lo + hi) / 2;
        double dl = rec(pts, lo, mid);
        double dr = rec(pts, mid + 1, hi);
        double d = Math.min(dl, dr);
        return Math.min(d, stripClosest(pts, lo, hi, mid, d));
    }

    private static double brute(Point[] pts, int lo, int hi) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++)
            for (int j = i+1; j <= hi; j++)
                best = Math.min(best, dist(pts[i], pts[j]));
        return best;
    }

    private static double stripClosest(Point[] pts, int lo, int hi, int mid, double d) {
        List<Point> strip = new ArrayList<>();
        double midX = pts[mid].x;
        for (int i = lo; i <= hi; i++)
            if (Math.abs(pts[i].x - midX) < d)
                strip.add(pts[i]);
        strip.sort(Comparator.comparingDouble(p -> p.y));
        double best = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i+1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < best; j++) {
                best = Math.min(best, dist(strip.get(i), strip.get(j)));
            }
        }
        return best;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
