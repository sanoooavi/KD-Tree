public class StateRectangle {
    private final int X_min, Y_min;   // minimum x- and y-coordinates
    private final int X_max, Y_max;   // maximum x- and y-coordinates

    public StateRectangle(int xmin, int ymin, int xmax, int ymax) throws Exception {
        if (xmax <= xmin || ymax <= ymin) {
            throw new Exception("Invalid Rectangle");
        }
        this.X_min = xmin;
        this.Y_min = ymin;
        this.X_max = xmax;
        this.Y_max = ymax;
    }

    public int contains(Branch p, boolean H) {
        if (H) {
            if (p.X() >= X_min && p.X() <= X_max)
                return 1;
            else if (p.X() > X_max)
                return 0;
            else
                return -1;

        } else {
            if (p.Y() >= Y_min && p.Y() <= Y_max)
                return 1;
            else if (p.Y() > Y_max)
                return 0;
            else
                return -1;
        }
    }

}
