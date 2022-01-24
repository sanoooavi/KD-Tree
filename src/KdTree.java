public class KdTree {
    private Node root;


    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    //insert a new point
    public void insert(String inf, Point point) {
        root=insert(root, point, inf, true);
    }

    private Node insert(Node root, Point point, String inf, boolean divX) {
        if (root == null)
            root = new Node(inf, point);
        Point node_point = root.getBranch().getPoint();
        if (node_point.equals(point)) {
            System.out.println("\"This Coordinate Is Taken, Please Try again.\"");
            return root;
        }
        int cmp = divX ? Point.X_ORDER(node_point, point) : Point.Y_ORDER(node_point, point);
        if (cmp < 0)
            root.left = insert(root.left, point, inf, !divX);
        else
            root.right = insert(root.right, point, inf, !divX);
        return root;
    }
}
