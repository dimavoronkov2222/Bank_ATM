import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        ATM atm1 = new ATM(50, 100);
        ATM atm2 = new ATM(20, 50);
        Map<Integer, Integer> initialCash = new HashMap<>();
        initialCash.put(500, 10);
        initialCash.put(200, 20);
        initialCash.put(100, 50);
        atm1.loadCash(initialCash);
        atm2.loadCash(initialCash);
        bank.addATM(atm1);
        bank.addATM(atm2);
        System.out.println("Welcome to the bank!");
        while (true) {
            System.out.println("\nChoose client type:");
            System.out.println("1. Administrator");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleAdminMenu(scanner, bank);
                    break;
                case 2:
                    handleCustomerMenu(scanner, bank);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void handleAdminMenu(Scanner scanner, Bank bank) {
        System.out.println("\nAdministrator Menu:");
        System.out.println("1. Add a new ATM");
        System.out.println("2. View total funds in all ATMs");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter minimum withdrawal amount: ");
                int minWithdraw = scanner.nextInt();
                System.out.print("Enter maximum banknotes per transaction: ");
                int maxNotes = scanner.nextInt();
                ATM newATM = new ATM(minWithdraw, maxNotes);
                bank.addATM(newATM);
                System.out.println("New ATM added to the network!");
                break;
            case 2:
                System.out.println("Total funds in all ATMs: " + bank.getTotalFunds());
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    private static void handleCustomerMenu(Scanner scanner, Bank bank) {
        System.out.println("\nCustomer Menu:");
        System.out.println("1. Withdraw money");
        System.out.println("2. Deposit money");
        System.out.println("3. View ATM balances");
        System.out.println("4. Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                try {
                    System.out.print("Enter ATM number (1-" + bank.getATMCount() + "): ");
                    int atmNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    int amount = scanner.nextInt();
                    ATM selectedATM = bank.getATM(atmNumber - 1);
                    Map<Integer, Integer> withdrawnCash = selectedATM.withdrawCash(amount);
                    System.out.println("You withdrew: " + withdrawnCash);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.print("Enter ATM number (1-" + bank.getATMCount() + "): ");
                    int atmNumber = scanner.nextInt();
                    System.out.println("Enter banknotes to deposit (format: denomination quantity, separated by spaces, e.g., 100 10):");
                    scanner.nextLine();
                    String[] input = scanner.nextLine().split(",");
                    Map<Integer, Integer> deposit = new HashMap<>();
                    for (String pair : input) {
                        String[] values = pair.trim().split(" ");
                        int denomination = Integer.parseInt(values[0]);
                        int count = Integer.parseInt(values[1]);
                        deposit.put(denomination, count);
                    }
                    ATM selectedATM = bank.getATM(atmNumber - 1);
                    selectedATM.depositCash(deposit);
                    System.out.println("Money deposited successfully!");
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                bank.displayATMs();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
}