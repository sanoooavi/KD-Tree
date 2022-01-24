public class Branch {
    private Point point;
    private String name_of_theBranch;
    private String bank;

    public Branch(String name_of_theBranch, String bank, Point point) {
        this.bank = bank;
        this.name_of_theBranch = name_of_theBranch;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

}
