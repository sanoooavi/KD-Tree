public class Node {
    private final Branch branch;
    Node left;
    Node right;

    public Branch getBranch() {
        return branch;
    }

    public Node(Branch branch) {
        this.branch = branch;
    }

}
