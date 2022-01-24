public class Node {
    private final Branch branch;
    Node left;
    Node right;

    public Branch getBranch() {
        return branch;
    }

    public Node(String inf, Point point) {
        String[] str = inf.split(" ");
        this.branch = new Branch(str[0], str[1], point);

    }


}
