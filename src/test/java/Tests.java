import com.helpicantchoose.Metrics;
import com.helpicantchoose.DeterministicSelect;
import com.helpicantchoose.ClosestPair;
import com.helpicantchoose.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class Tests {
    @Test
    void testSelectCorrectnessRandomTrials() {
        Metrics m = new Metrics();
        Random rnd = new Random(42);
        int trials = 100;
        for (int trial = 0; trial < trials; trial++) {
            int[] testArr = rnd.ints(1000, 0, 100000).toArray();
            int k = rnd.nextInt(testArr.length);
            m.reset();
            int sel = DeterministicSelect.select(testArr.clone(), k, m);

            int less = 0, equal = 0;
            for (int v : testArr) {
                if (v < sel) less++;
                if (v == sel) equal++;
            }
            assertTrue(less <= k && less + equal > k,
                    "Select failed: k=" + k + ", got=" + sel + ", less=" + less + ", equal=" + equal);
        }
    }

    @Test
    void testClosestPairBruteForceValidation() {
        Random rnd = new Random(42);
        for (int trial = 0; trial < 10; trial++) {
            int smallN = 100 + rnd.nextInt(100);
            Point[] ptsSmall = new Point[smallN];
            for (int i = 0; i < smallN; i++)
                ptsSmall[i] = new Point(rnd.nextDouble(), rnd.nextDouble());

            double minDist = Double.POSITIVE_INFINITY;
            for (int i = 0; i < smallN; i++)
                for (int j = i + 1; j < smallN; j++)
                    minDist = Math.min(minDist, ptsSmall[i].dist(ptsSmall[j]));

            double fastDist = ClosestPair.closest(ptsSmall);
            assertTrue(Math.abs(minDist - fastDist) <= 1e-9,
                    "ClosestPair failed: got=" + fastDist + ", expected=" + minDist);
        }
    }
}
