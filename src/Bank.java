public class Bank {
    private ATM[] atms;
    private int atmCount;
    public Bank(int maxATMs) {
        this.atms = new ATM[maxATMs];
        this.atmCount = 0;
    }
    public void addATM(int minWithdrawal, int maxNotes) throws ATMException {
        if (atmCount >= atms.length) {
            throw new ATMException("Cannot add more ATMs, limit reached.");
        }
        atms[atmCount++] = new ATM(minWithdrawal, maxNotes);
        System.out.println("ATM added successfully.");
    }
    public void loadMoneyToATM(int atmIndex, int[] cash) throws ATMException {
        if (atmIndex < 0 || atmIndex >= atmCount) {
            throw new ATMException("Invalid ATM index.");
        }
        atms[atmIndex].loadCash(cash);
    }
    public void displayATMs() {
        if (atmCount == 0) {
            System.out.println("No ATMs in the network.");
        } else {
            for (int i = 0; i < atmCount; i++) {
                System.out.println("ATM #" + (i + 1) + ":");
                atms[i].displayStatus();
            }
        }
    }
    public ATM getATM(int index) throws ATMException {
        if (index < 0 || index >= atmCount) {
            throw new ATMException("Invalid ATM index.");
        }
        return atms[index];
    }
    public int getATMCount() {
        return atmCount;
    }
}