import java.util.ArrayList;
import java.util.List;
public class Bank {
    private List<ATM> atms;
    public Bank() {
        this.atms = new ArrayList<>();
    }
    public void addATM(ATM atm) {
        atms.add(atm);
    }
    public int getTotalFunds() {
        return atms.stream().mapToInt(ATM::getTotalAmount).sum();
    }
    public void displayATMs() {
        for (int i = 0; i < atms.size(); i++) {
            System.out.println("ATM #" + (i + 1) + ": " + atms.get(i));
        }
    }
}