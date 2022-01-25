
public class KdTree {
    private Node root;
    public static final int dim = 2;
    Branch bankMainGlobal = null;

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    public Node Root() {
        return root;
    }

    //insert a new point
    public Branch insert(Branch newBranch) {
        root = insert(root, newBranch, true);
        return bankMainGlobal;
    }

    private Node insert(Node root, Branch newBranch, boolean divX) {
        if (root == null) {
            bankMainGlobal = newBranch;
            System.out.println("The branch was successfully added to the map");
            return new Node(newBranch);
        }
        if (root.getBranch().equal_Coordinate(newBranch)) {
            bankMainGlobal = null;
            System.out.println("This Coordinate Is Taken, Please Try again.");
            return root;
        }
        int cmp = divX ? Branch.X_ORDER(root, newBranch) : Branch.Y_ORDER(root, newBranch);
        if (cmp == 0)
            root.left = insert(root.left, newBranch, !divX);
        else
            root.right = insert(root.right, newBranch, !divX);
        return root;
    }

    public void delete(int[] coordinate) {

        delete(root, coordinate, 0);
    }

    Node delete(Node root, int[] coo, int d) {
        if (root == null) {
            return null;
        }
        int l = d % dim;
        if (root.getBranch().equalsC00(coo)) {
            if (root.right != null) {
                Node min = findMin(root.right, l, 0);
                root.getBranch().changeNode(min.getBranch());
                root.right = delete(root.right, min.getBranch().Coo(), d + 1);
            } else if (root.left != null) {
                Node min = findMin(root.left, l, 0);
                root.getBranch().changeNode(min.getBranch());
                root.right = delete(root.left, min.getBranch().Coo(), d + 1);
            } else {
                root = null;
            }
            return root;
        }
        if (coo[l] < root.getBranch().Coo()[l]) {
            root.left = delete(root.left, coo, d + 1);
        } else {
            root.right = delete(root.right, coo, d + 1);
        }
        return root;
    }

    private Node findMin(Node root, int e, int d) {
        if (root == null) {
            return null;
        }
        int l = d % dim;
        if (l == e) {
            if (root.left == null) {
                return root;
            }
            return findMin(root.left, e, d + 1);
        }

        return Min(root, findMin(root.left, e, d + 1), findMin(root.right, e, d + 1), d);
    }

    Node Min(Node a, Node b, Node c, int d) {

        Node temp = a;
        if (b != null && b.getBranch().Coo()[d] < temp.getBranch().Coo()[d]) {
            temp = b;
        }
        if (c != null && c.getBranch().Coo()[d] < temp.getBranch().Coo()[d]) {
            temp = c;
        }
        return temp;
    }
    void NodeInArea(StateRectangle sta){
        printNodesInArea(root,sta,true);
    }

    void printNodesInArea(Node root, StateRectangle area, boolean isXBase) {
        if (root == null)
            return;
        int containsResult = area.contains(root.getBranch(), isXBase);
        if (containsResult == 1) {
            printNodesInArea(root.right, area, !isXBase);
            printNodesInArea(root.left, area, !isXBase);
        } else if (containsResult == 0) {
            printNodesInArea(root.left, area, !isXBase);
        } else {
            printNodesInArea(root.left, area, !isXBase);
        }
        if (containsResult == 0 && area.contains(root.getBranch(), !isXBase) == 0) {
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
}
