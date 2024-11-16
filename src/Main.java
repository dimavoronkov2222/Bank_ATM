import java.util.HashMap;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        try {
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
            System.out.println("Total funds in bank: " + bank.getTotalFunds());
            Map<Integer, Integer> withdrawnCash = atm1.withdrawCash(1800);
            System.out.println("Withdrawn: " + withdrawnCash);
            System.out.println("Total funds after withdrawal: " + bank.getTotalFunds());
        } catch (ATMException e) {
            System.err.println("ATM Error: " + e.getMessage());
        }
    }
}