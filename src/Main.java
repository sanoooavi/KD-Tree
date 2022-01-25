import java.util.Scanner;

public class Main {
    private static TrieDataStructure districts = new TrieDataStructure();
    private static TrieDataStructure bank_main = new TrieDataStructure();
    private static KdTree kdTree = new KdTree();

    public static void main(String[] args) {
        System.out.println("Welcome to the Banks Management System");
        System.out.println("Choose What You Want to Do:");
        options();
        Scanner input = new Scanner(System.in);
        int order = input.nextInt();
        while (order < 1 || order > 12) {
            System.out.println("Enter a Valid Number");
            order = input.nextInt();
        }
        String BankName, BranchName;
        int x, y, x_min, x_max, y_min, y_max;
        switch (order) {
            case 1:
                System.out.println("Enter Name of the Neighbourhood:");
                String districtName = input.next();
                System.out.print("x_min:");
                x_min = input.nextInt();
                System.out.print("x_max:");
                x_max = input.nextInt();
                System.out.print("y_min:");
                y_min = input.nextInt();
                System.out.print("y_max:");
                y_max = input.nextInt();
                addN(x_min, y_min, x_max, y_max, districtName);
                nextOrder();
                break;
            case 2:
                System.out.println("Enter Name of the Bank:");
                BankName = input.next();
                System.out.println("Enter Banks's Coordinate:");
                System.out.print("x:");
                x = input.nextInt();
                System.out.print("y:");
                y = input.nextInt();
                boolean added = addB(x, y, BankName);
                if (added) {
                    System.out.println("Do you want to add branches?\n1)Yes 2)No");
                    int num = input.nextInt();
                    while (num == 1) {
                        System.out.println("Enter Name of the Branch:");
                        BranchName = input.next();
                        System.out.println("Enter Branch's Coordinate:");
                        System.out.print("x:");
                        x = input.nextInt();
                        System.out.print("y:");
                        y = input.nextInt();
                        addBr(x, y, BankName, BranchName);
                        System.out.println("Do you want to add branches?\n1)Yes 2)No");
                        num = input.nextInt();
                    }
                }
                nextOrder();
                break;
            case 3:
                System.out.println("Enter Name of the Main_Bank:");
                BankName = input.next();
                if (!bank_main.search(BankName))
                    System.out.println("There is No Bank with this name.Please try again");
                else {
                    System.out.println("Enter Name of the Branch:");
                    BranchName = input.next();
                    System.out.println("Enter Branch's Coordinate:");
                    System.out.print("x :");
                    x = input.nextInt();
                    System.out.print("y :");
                    y = input.nextInt();
                    addBr(x, y, BankName, BranchName);
                }
                nextOrder();
                break;
            case 4:
                System.out.println("Enter Coordinate of the Branch You Want to Delete");
                System.out.print("x :");
                x = input.nextInt();
                System.out.print("y :");
                y = input.nextInt();
                delBr(x, y);
                nextOrder();
                break;
            case 5:
                System.out.println("Enter Name of the Neighbourhood:");
                districtName = input.next();
                if (!districts.search(districtName)) {
                    System.out.println("This District Doesn't Exist, Enter a Valid Value:");
                } else
                    listB(districtName);
                nextOrder();
                break;
            case 6:
                System.out.println("Enter Name of the Bank:");
                BankName = input.next();
                if (!bank_main.search(BankName))
                    System.out.println("There is No Bank with this name.Please try again");
                else {
                    listBrs(BankName);
                }
                nextOrder();
                break;
            case 7:
                System.out.println("Enter your coordinates");
                System.out.print("x :");
                x = input.nextInt();
                System.out.print("y :");
                y = input.nextInt();
                nearB(x, y);
                nextOrder();
                break;
            case 8:
                System.out.println("Enter Name of the Bank:");
                BankName = input.next();
                if (!bank_main.search(BankName))
                    System.out.println("There is No Bank with this name.Please try again");
                else {
                    System.out.println("Enter Coordinate of the Point:");
                    x = input.nextInt();
                    y = input.nextInt();
                    nearBr(x, y, BankName);
                }
                nextOrder();
                break;
        }


    }


    private static void options() {
        System.out.println("1) Add District");
        System.out.println("2) Add Bank");
        System.out.println("3) Add Branch");
        System.out.println("4) Delete Branch");
        System.out.println("5) In-Neighborhood Banks");
        System.out.println("6) Branches of the Bank");
        System.out.println("7) Nearest Bank");
        System.out.println("8) Nearest Branch");
    }

    private static void nextOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("What's Your Next Order?\n1. Back to Main Menu\n2. Exit");
        int order = input.nextInt();
        while (order != 1 && order != 2) {
            System.out.println("Invalid Input, Enter a Valid Value:");
            order = input.nextInt();
        }
        if (order == 1)
            main(new String[0]);
        else
            System.exit(0);
    }

    static void addN(int x_min, int y_min, int x_max, int y_max, String districtName) {
        StateRectangle rect;
        try {
            rect = new StateRectangle(x_min, y_min, x_max, y_max);
            districts.insert(districtName, rect);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static boolean addB(int x, int y, String bankName) {
        Branch newBranch = new Branch(bankName, new int[]{x, y});
        Branch bank = kdTree.insert(newBranch);
        if (bank != null)
            bank_main.insert(bankName, bank);
        return bank != null;
    }

    private static void addBr(int x, int y, String bankName, String branchName) {
        Branch newBranch = new Branch(branchName, bankName, new int[]{x, y});
        Branch bank = kdTree.insert(newBranch);
        if (bank != null)
            ((Branch) bank_main.get(bankName)).children.insert(bank);
    }

    private static void delBr(int x, int y) {
        kdTree.delete(new int[]{x, y});
    }

    private static void listB(String districtName) {
        StateRectangle sta = (StateRectangle) districts.get(districtName);
        kdTree.NodeInArea(sta);
    }

    private static void nearB(int x, int y) {


    }

    private static void listBrs(String bankName) {
        System.out.println();
        kdTree.printPreorder(((Branch) bank_main.get(bankName)).children.Root());
        System.out.println();
    }

    private static void nearBr(int x, int y, String bankName) {
    }


}





