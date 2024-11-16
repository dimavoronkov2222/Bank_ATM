public class InsufficientFundsException extends ATMException {
    private int availableFunds;
    public InsufficientFundsException(String message, int availableFunds) {
        super(message);
        this.availableFunds = availableFunds;
    }
    public int getAvailableFunds() {
        return availableFunds;
    }
}