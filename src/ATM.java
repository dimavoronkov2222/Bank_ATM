import java.util.Map;
import java.util.TreeMap;
public class ATM {
    private Map<Integer, Integer> banknotes;
    private int minWithdrawAmount;
    private int maxBanknotes;
    public ATM(int minWithdrawAmount, int maxBanknotes) {
        this.banknotes = new TreeMap<>((a, b) -> b - a);
        this.minWithdrawAmount = minWithdrawAmount;
        this.maxBanknotes = maxBanknotes;
    }
    public void loadCash(Map<Integer, Integer> initialCash) {
        initialCash.forEach((denomination, count) ->
                banknotes.put(denomination, banknotes.getOrDefault(denomination, 0) + count)
        );
    }
    public void depositCash(Map<Integer, Integer> deposit) {
        loadCash(deposit);
    }
    public Map<Integer, Integer> withdrawCash(int amount) throws ATMException {
        if (amount < minWithdrawAmount) {
            throw new InvalidAmountException("Amount is less than the minimum withdrawal limit.");
        }
        Map<Integer, Integer> result = new TreeMap<>((a, b) -> b - a);
        int totalNotes = 0;
        for (Map.Entry<Integer, Integer> entry : banknotes.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();
            int needed = Math.min(amount / denomination, count);
            if (needed > 0) {
                result.put(denomination, needed);
                amount -= needed * denomination;
                totalNotes += needed;
            }
        }
        if (amount > 0) {
            throw new InsufficientFundsException("Not enough banknotes to fulfill the withdrawal.");
        }
        if (totalNotes > maxBanknotes) {
            throw new InvalidAmountException("Withdrawal exceeds maximum banknotes limit.");
        }
        result.forEach((denomination, count) ->
                banknotes.put(denomination, banknotes.get(denomination) - count)
        );
        return result;
    }
    public int getTotalAmount() {
        return banknotes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }
    @Override
    public String toString() {
        return "ATM { Total Amount: " + getTotalAmount() + ", Banknotes: " + banknotes + " }";
    }
}