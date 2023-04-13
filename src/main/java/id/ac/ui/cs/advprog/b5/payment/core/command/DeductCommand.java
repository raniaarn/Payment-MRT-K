package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public class DeductCommand implements WalletCommand {
    @Override
    public String execute(Wallet wallet, double amount) {
        wallet.updateBalance(-amount);
        return "your balance is now " + wallet.getBalance();
    }

    @Override
    public String getName() {
        return "DEDUCT";
    }
}
