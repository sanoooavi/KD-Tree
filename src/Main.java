import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, StateRectangle> districts = new HashMap<>();
    private static HashMap<String, Point> bank_main = new HashMap<>();
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
        double x, y, x_min, x_max, y_min, y_max;
        switch (order) {
            case 1:
                System.out.println("Enter Name of the Neighbourhood:");
                String districtName = input.next();
                System.out.println("x_min:");
                x_min = input.nextDouble();
                System.out.println("x_max:");
                x_max = input.nextDouble();
                System.out.println("y_min:");
                y_min = input.nextDouble();
                System.out.println("y_max:");
                y_max = input.nextDouble();
                addN(x_min, y_min, x_max, y_max, districtName);
                nextOrder();
                break;
            case 2:
                System.out.println("Enter Name of the Bank:");
                BankName = input.next();
                System.out.println("Enter Banks's Coordinate:");
                System.out.println("x:");
                x = input.nextDouble();
                System.out.println("y:");
                y = input.nextDouble();
                addB(x, y, BankName);
                System.out.println("Do you want to add branches?\n1)Yes 2)No");
                int num = input.nextInt();
                while (num == 1) {
                    System.out.println("Enter Name of the Branch:");
                    BranchName = input.next();
                    System.out.println("Enter Branch's Coordinate:");
                    System.out.println("x:");
                    x = input.nextDouble();
                    System.out.println("y:");
                    y = input.nextDouble();
                    addBr(x, y, BankName, BranchName);
                    System.out.println("Do you want to add branches?\n1)Yes 2)No");
                    num = input.nextInt();
                }
                nextOrder();
                break;
            case 3:
                System.out.println("Enter Name of the Main_Bank:");
                BankName = input.next();
                System.out.println("Enter Name of the Branch:");
                BranchName = input.next();
                System.out.println("Enter Branch's Coordinate:");
                System.out.println("x:");
                x = input.nextDouble();
                System.out.println("y:");
                y = input.nextDouble();
                addBr(x, y, BankName, BranchName);
                nextOrder();
                break;
            case 5:
                System.out.println("Enter Name of the Neighbourhood:");
                districtName = input.next();
                if (!districts.containsKey(districtName)) {
                    System.out.println("This District Doesn't Exist, Enter a Valid Value:");
                } else
                    listB(districtName);
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

    static void addN(double x_min, double y_min, double x_max, double y_max, String districtName) {
        StateRectangle rect ;
        try {
            rect = new StateRectangle(x_min, y_min, x_max, y_max);
            districts.put(districtName, rect);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static void addB(double x, double y, String bankName) {
        Point point;
        try {
            point = new Point(x, y);
            kdTree.insert(bankName + " " + bankName, point);
            bank_main.put(bankName, point);
            System.out.println("The bank was successfully added to the map");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void addBr(double x, double y, String bankName, String branchName) {
        Point point;
        try {
            point = new Point(x, y);
            kdTree.insert(bankName + " " + branchName, point);
            System.out.println("The branch was successfully added to the map");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static void listB(String districtName) {
        StateRectangle sta = districts.get(districtName);
        kdTree.isInState(sta);
    }
}





