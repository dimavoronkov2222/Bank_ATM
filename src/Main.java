import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(10);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add ATM (Admin)");
            System.out.println("2. Load Money to ATM (Admin)");
            System.out.println("3. View All ATMs (Admin)");
            System.out.println("4. Deposit Money to Account (Customer)");
            System.out.println("5. Withdraw Money from ATM (Customer)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter minimum withdrawal amount: ");
                        int minWithdrawal = scanner.nextInt();
                        System.out.print("Enter maximum notes per transaction: ");
                        int maxNotes = scanner.nextInt();
                        bank.addATM(minWithdrawal, maxNotes);
                        break;
                    case 2:
                        System.out.print("Enter ATM index to load money: ");
                        int atmIndex = scanner.nextInt() - 1;
                        if (atmIndex < 0 || atmIndex >= bank.getATMCount()) {
                            System.out.println("Invalid ATM index.");
                            break;
                        }
                        int[] cash = new int[9];
                        System.out.println("Enter cash to load (by denomination):");
                        for (int i = 0; i < cash.length; i++) {
                            System.out.print(bank.getATM(atmIndex).denominations[i] + " UAH: ");
                            cash[i] = scanner.nextInt();
                        }
                        bank.loadMoneyToATM(atmIndex, cash);
                        break;
                    case 3:
                        bank.displayATMs();
                        break;
                    case 4:
                        System.out.print("Enter account holder name: ");
                        scanner.nextLine(); // consume newline
                        String accountHolder = scanner.nextLine();
                        BankAccount account = new BankAccount(accountHolder, 0);
                        System.out.print("Enter deposit amount: ");
                        int depositAmount = scanner.nextInt();
                        account.deposit(depositAmount);
                        break;
                    case 5:
                        System.out.print("Enter ATM index to withdraw money: ");
                        atmIndex = scanner.nextInt() - 1;
                        if (atmIndex < 0 || atmIndex >= bank.getATMCount()) {
                            System.out.println("Invalid ATM index.");
                            break;
                        }
                        System.out.print("Enter withdrawal amount: ");
                        int withdrawAmount = scanner.nextInt();
                        BankAccount userAccount = new BankAccount("Default", 1000); // Example account with some balance
                        ATM atm = bank.getATM(atmIndex);
                        atm.withdrawFromAccount(userAccount, withdrawAmount);
                        System.out.println("Successfully withdrew money. Remaining balance: " + userAccount.getBalance());
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (ATMException e) {
                System.out.println("Error: " + e.getMessage());
                if (e instanceof InsufficientFundsException) {
                    System.out.println("Available funds: " + ((InsufficientFundsException) e).getAvailableFunds());
                }
            }
        }
    }
}