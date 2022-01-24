
public class KdTree {
    private Node root;


    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    //insert a new point
    public void insert(String inf, Point point) {
        root = insert(root, point, inf, true);
    }

    private Node insert(Node root, Point point, String inf, boolean divX) {
        if (root == null)
            return new Node(inf, point);
        Point node_point = root.getBranch().getPoint();
        if (node_point.equals(point)) {
            System.out.println("This Coordinate Is Taken, Please Try again.");
            return root;
        }
        int cmp = divX ? Point.X_ORDER(node_point, point) : Point.Y_ORDER(node_point, point);
        if (cmp == 0)
            root.left = insert(root.left, point, inf, !divX);
        else
            root.right = insert(root.right, point, inf, !divX);
        return root;
    }

    void isInState(StateRectangle sta) {
        isInState(root, sta, true);
    }

    public void isInState(Node root, StateRectangle sta, boolean H) {
        if (root == null)
            return;
        int containsResult = sta.contains(root.getBranch().getPoint(), H);
        if (containsResult == 1) {
            isInState(root.right, sta, !H);
            isInState(root.left, sta, !H);
        } else if (containsResult <0) {
            isInState(root.left, sta, !H);
        } else {
            isInState(root.right, sta, !H);
        }
        if (containsResult ==1 ) {
            System.out.println();
        }
    }
}
