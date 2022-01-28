
public class KdTree {
    private Node root;
    public static final int dim = 2;
    Branch bankMainGlobal = null;
    Branch deleted_Bank = null;
    int size = 0;

    public KdTree() {
        root = null;
    }

    public Node Root() {
        return root;
    }

    public Branch insert(Branch newBranch) {
        root = insert(root, newBranch, 0);
        return bankMainGlobal;
    }

    private Node insert(Node root, Branch newBranch, int d) {
        if (root == null) {
            bankMainGlobal = newBranch;
            size++;
            return new Node(newBranch);
        }
        d %= dim;
        if (root.getBranch().equal_Coordinate(newBranch)) {
            bankMainGlobal = null;
            System.out.println("This Coordinate Is Taken, Please Try again.");
            return root;
        }
        int cmp = (d == 0) ? Branch.X_ORDER(root, newBranch) : Branch.Y_ORDER(root, newBranch);
        if (cmp == 0)
            root.left = insert(root.left, newBranch, d + 1);
        else
            root.right = insert(root.right, newBranch, d + 1);
        return root;
    }

    public Branch delete(int[] coordinate) {
        root = delete(root, coordinate, 0);
        Branch returnBranch = deleted_Bank;
        deleted_Bank = null;
        return returnBranch;
    }

    private Node delete(Node root, int[] coo, int d) {
        if (root == null) {
            return null;
        }
        d %= dim;
        if (root.getBranch().equalsC00(coo)) {
            if (root.getBranch().isMainBank())
                System.out.println("You can not delete a main bank");
            else {
                deleted_Bank = root.getBranch();
                size--;
                if (root.right != null) {
                    Node min = findMin(root.right, d, d);
                    root.changeNode(min);
                    root.right = delete(root.right, min.getBranch().Coo(), d + 1);
                } else if (root.left != null) {
                    Node min = findMin(root.left, d, d);
                    root.changeNode(min);
                    root.right = delete(root.left, min.getBranch().Coo(), d + 1);
                } else {
                    root = null;
                }
            }
            return root;
        }
        if (coo[d] < root.getBranch().Coo()[d]) {
            root.left = delete(root.left, coo, d + 1);
        } else {
            root.right = delete(root.right, coo, d + 1);
        }
        return root;
    }

    private Node findMin(Node root, int targetDim, int d) {
        if (root == null)
            return null;
        d %= dim;
        if (targetDim == d) {
            if (root.left == null)
                return root;
            return findMin(root.left, targetDim, d + 1);
        }
        Node rightMin = findMin(root.right, targetDim, d + 1);
        Node leftMin = findMin(root.left, targetDim, d + 1);
        Node res = root;
        if (rightMin != null && rightMin.getBranch().Coo()[targetDim] < res.getBranch().Coo()[targetDim])
            res = rightMin;
        if (leftMin != null && leftMin.getBranch().Coo()[targetDim] < res.getBranch().Coo()[targetDim])
            res = leftMin;
        return res;
    }

    void NodeInArea(StateRectangle sta) {
        printNodesInArea(root, sta, 0);
    }

    public void InArea(StateRectangle sq, int[] ints, int radius) {
        printNodesInCircle(root, sq, 0, ints, radius);
    }

    private void printNodesInArea(Node root, StateRectangle sta, int d) {
        if (root == null)
            return;
        d %= dim;
        int res = sta.contains(root.getBranch(), d);
        int res1 = sta.contains(root.getBranch(), d + 1);
        if (res == 1) {
            printNodesInArea(root.right, sta, d + 1);
            printNodesInArea(root.left, sta, d + 1);
        } else if (res == 0) {
            printNodesInArea(root.left, sta, d + 1);
        } else {
            printNodesInArea(root.left, sta, d + 1);
        }
        if (res == 1 && res1 == 1) {
            System.out.println(root.getBranch().toString());
        }
    }

    private void printNodesInCircle(Node root, StateRectangle sq, int d, int[] p, int r) {
        if (root == null)
            return;
        int containsResult = sq.contains(root.getBranch(), d);
        if (containsResult == 1) {
            printNodesInCircle(root.right, sq, d + 1, p, r);
            printNodesInCircle(root.left, sq, d + 1, p, r);
        } else if (containsResult == 0) {
            printNodesInCircle(root.left, sq, d + 1, p, r);
        } else {
            printNodesInCircle(root.left, sq, d + 1, p, r);
        }
        if (containsResult == 1 && sq.contains(root.getBranch(), d + 1) == 1 && distance(p, root.getBranch().Coo()) <= r * r) {
            System.out.println(root.getBranch().toString());
        }
    }

    void printPreorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.getBranch().toString() + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public Branch nearest(int[] coo) {
        if (root == null) return null;
        return nearest(root, coo, root.getBranch(), 0);
    }

    private int distance(int[] a, int[] b) {
        int dx = (a[0] - b[0]) * (a[0] - b[0]);
        int dy = (a[1] - b[1]) * (a[1] - b[1]);
        return dx + dy;
    }

    private Branch nearest(Node root, int[] p, Branch mp, int d) {
        Branch min = mp;
        d %= dim;
        if (root == null) return min;
        if (distance(p, root.getBranch().Coo()) < distance(p, min.Coo()))
            min = root.getBranch();
        if (d == 0 && root.getBranch().X() < p[0] || d == 1 && root.getBranch().Y() < p[1]) {
            min = nearest(root.right, p, min, d + 1);
            if (root.left != null && distance(min.Coo(), p) > distance(p, root.left.getBranch().Coo()))
                min = nearest(root.left, p, min, d + 1);
        } else {
            min = nearest(root.left, p, min, d + 1);
            if (root.right != null && distance(min.Coo(), p) > distance(p, root.right.getBranch().Coo()))
                min = nearest(root.right, p, min, d + 1);
        }
        return min;
    }


}
