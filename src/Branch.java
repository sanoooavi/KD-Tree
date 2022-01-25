import java.util.Arrays;

public class Branch {
    private int[] coordinate;
    private String name_of_theBranch = null;
    private String bank = null;
    boolean isMainBank = false;
    KdTree children;

    public Branch(String bank, int[] coordinate) {
        this.bank = bank;
        this.coordinate = coordinate;
        this.isMainBank = true;
        children = new KdTree();
    }


    public Branch(String name_of_theBranch, String bank, int[] coordinate) {
        this.bank = bank;
        this.name_of_theBranch = name_of_theBranch;
        this.coordinate = coordinate;
    }

    public static int X_ORDER(Node root, Branch newNode) {
        if (newNode.X() >= root.getBranch().X())
            return 1;
        return 0;
    }

    public static int Y_ORDER(Node root, Branch newNode) {
        if (newNode.Y() >= root.getBranch().Y())
            return 1;
        return 0;
    }

    public void changeNode(Branch branch) {
        this.coordinate = branch.coordinate;
        this.name_of_theBranch = branch.name_of_theBranch;
        this.bank = branch.bank;
        this.isMainBank = branch.isMainBank;
    }

    public int[] Coo() {
        return coordinate;
    }


    public int X() {
        return coordinate[0];
    }

    public int Y() {
        return coordinate[1];
    }

    public boolean equal_Coordinate(Branch that) {
        return (that.coordinate[0] == this.coordinate[0] && that.coordinate[1] == this.coordinate[1]);
    }

    public boolean equalsC00(int[] that) {
        return (that[0] == this.coordinate[0] && that[1] == this.coordinate[1]);
    }


    @Override
    public String toString() {
        return "Branch { " + " coordinate = " + Arrays.toString(coordinate) + ", name_of_theBranch = '" + name_of_theBranch + '\'' + ", bank = '" + bank + '\'' + ", isMainBank = " + isMainBank + '}';
    }
}
