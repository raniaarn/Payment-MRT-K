package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public class TopUpCommand implements WalletCommand {
    @Override
    public String execute(Wallet wallet, double amount) {
        // perform top-up
        wallet.reduceBalance(amount);
        return "your balance is now " + wallet.getBalance();
    }
}
