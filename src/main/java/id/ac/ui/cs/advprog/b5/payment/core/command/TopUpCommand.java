package id.ac.ui.cs.advprog.b5.payment.core.command;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public class TopUpCommand implements WalletCommand {

    private double amount;

    @Override
    public String execute(Wallet wallet, double amount) {
        // perform top-up
        wallet.updateBalance(amount);
        this.amount = amount;
        return "Top up succeed. your balance is now " + wallet.getBalance();
    }

    @Override
    public String getName() {
        return "TOPUP " + amount;
    }
}
