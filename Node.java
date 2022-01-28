public class Node {
    private Branch branch;
    Node left = null;
    Node right = null;
    Node next = null;

    public Branch getBranch() {
        return branch;
    }

    public Node(Branch branch) {
        this.branch = branch;
    }

    public void changeNode(Node min) {
        this.branch = min.getBranch();
    }
}
