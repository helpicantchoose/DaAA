import com.helpicantchoose.ClosestPair;
import com.helpicantchoose.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    @Test
    void testSimpleCase() {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1)
        };
        double expected = Math.sqrt(2); // between (0,0) and (1,1)
        double result = ClosestPair.closest(pts);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testIdenticalPoints() {
        Point[] pts = {
                new Point(2, 2),
                new Point(2, 2),
                new Point(5, 5)
        };
        double result = ClosestPair.closest(pts);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testCollinear() {
        Point[] pts = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0)
        };
        double result = ClosestPair.closest(pts);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testLargeDistance() {
        Point[] pts = {
                new Point(0, 0),
                new Point(1000, 1000),
                new Point(0, 1)
        };
        double result = ClosestPair.closest(pts);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testNegativeCoordinates() {
        Point[] pts = {
                new Point(-1, -1),
                new Point(-2, -2),
                new Point(3, 3)
        };
        double expected = Math.sqrt(2);
        double result = ClosestPair.closest(pts);
        assertEquals(expected, result, 1e-9);
    }

    @Test
    void testTwoPoints() {
        Point[] pts = {
                new Point(1, 1),
                new Point(4, 5)
        };
        double expected = 5.0;
        double result = ClosestPair.closest(pts);
        assertEquals(expected, result, 1e-9);
    }
}
