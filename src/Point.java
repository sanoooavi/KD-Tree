import java.util.Objects;

public class Point  {
    private double x;    // x coordinate
    private double y;// y coordinate

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
    void changeNode(Point that){
        this.x=that.X();
        this.y=that.Y();
    }

    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public static int X_ORDER(Point p, Point q) {
        if (p.x < q.x) return 0;
        else return 1;
    }

    public static int Y_ORDER(Point p, Point q) {
        if (p.y < q.y) return 0;
        else return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
