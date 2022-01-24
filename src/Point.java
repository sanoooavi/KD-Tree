

public class Point implements Comparable<Point> {
    private final double x;    // x coordinate
    private final double y;// y coordinate

    public Point(double x, double y) throws Exception {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new Exception("Coordinates must be finite");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new Exception("Coordinates cannot be NaN");
        if (x == 0.0) x = 0.0;  // convert -0.0 to +0.0
        if (y == 0.0) y = 0.0;  // convert -0.0 to +0.0
        this.x = x;
        this.y = y;
    }

    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public static int X_ORDER(Point p, Point q) {
        return Double.compare(p.x, q.x);
    }

    public static int Y_ORDER(Point p, Point q) {
        return Double.compare(p.y, q.y);
    }
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        return Double.compare(this.x, that.x);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point that = (Point) other;
        return this.x == that.x && this.y == that.y;
    }
    @Override
    public int hashCode() {
        int hashX = ((Double) x).hashCode();
        int hashY = ((Double) y).hashCode();
        return 31*hashX + hashY;
    }
}
