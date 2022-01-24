public class StateRectangle {
    private final double X_min, Y_min;   // minimum x- and y-coordinates
    private final double X_max, Y_max;   // maximum x- and y-coordinates

    public StateRectangle(double xmin, double ymin, double xmax, double ymax) throws Exception {
        if (xmax < xmin || ymax < ymin) {
            throw new Exception("Invalid Rectangle");
        }
        this.X_min = xmin;
        this.Y_min = ymin;
        this.X_max = xmax;
        this.Y_max = ymax;
    }

}
