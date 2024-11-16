public class ATM {
    public int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500};
    private int[] notes;
    private int minWithdrawal;
    private int maxNotes;
    public ATM(int minWithdrawal, int maxNotes) {
        this.minWithdrawal = minWithdrawal;
        this.maxNotes = maxNotes;
        this.notes = new int[denominations.length];
    }
    public void loadCash(int[] cash) {
        for (int i = 0; i < denominations.length; i++) {
            notes[i] += cash[i];
        }
        System.out.println("ATM successfully loaded with cash.");
    }
    public int getTotalAmount() {
        int total = 0;
        for (int i = 0; i < denominations.length; i++) {
            total += denominations[i] * notes[i];
        }
        return total;
    }
    public int[] withdrawCash(int amount) throws ATMException {
        if (amount < minWithdrawal) {
            throw new ATMException("Amount is below the minimum withdrawal limit.");
        }
        if (amount > getTotalAmount()) {
            throw new ATMException("Insufficient funds in the ATM.");
        }
        return dispenseCash(amount);
    }
    private int[] dispenseCash(int amount) throws ATMException {
        int[] withdrawnNotes = new int[denominations.length];
        int count = 0;
        for (int i = denominations.length - 1; i >= 0 && amount > 0; i--) {
            int noteCount = Math.min(amount / denominations[i], notes[i]);
            if (noteCount > 0) {
                withdrawnNotes[i] = noteCount;
                notes[i] -= noteCount;
                amount -= noteCount * denominations[i];
                count += noteCount;
            }
        }
        if (amount > 0) {
            throw new ATMException("Cannot dispense exact amount due to insufficient denominations.");
        }
        if (count > maxNotes) {
            throw new ATMException("Cannot dispense requested amount: exceeds maximum notes per transaction.");
        }
        return withdrawnNotes;
    }
    public void withdrawFromAccount(BankAccount account, int amount) throws ATMException {
        if (amount < minWithdrawal) {
            throw new ATMException("Amount is below the minimum withdrawal limit.");
        }
        if (amount > getTotalAmount()) {
            throw new ATMException("Insufficient funds in the ATM.");
        }
        if (amount > account.getBalance()) {
            throw new ATMException("Insufficient funds in the account.");
        }
        int[] withdrawnNotes = dispenseCash(amount);
        account.withdraw(amount);
        System.out.println("Successfully withdrew:");
        for (int i = 0; i < denominations.length; i++) {
            if (withdrawnNotes[i] > 0) {
                System.out.println(denominations[i] + " UAH: " + withdrawnNotes[i] + " notes");
            }
        }
    }
    public void displayStatus() {
        System.out.println("ATM Status:");
        for (int i = 0; i < denominations.length; i++) {
            System.out.println(denominations[i] + " UAH: " + notes[i] + " notes");
        }
        System.out.println("Total amount: " + getTotalAmount());
    }
}