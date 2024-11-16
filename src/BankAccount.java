public class BankAccount {
    private String accountHolder;
    private int balance;
    public BankAccount(String accountHolder, int initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Successfully deposited " + amount + " UAH. Balance: " + balance);
    }
    public void withdraw(int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds in the account.");
        }
        balance -= amount;
    }
    public int getBalance() {
        return balance;
    }
    public String getAccountHolder() {
        return accountHolder;
    }
}