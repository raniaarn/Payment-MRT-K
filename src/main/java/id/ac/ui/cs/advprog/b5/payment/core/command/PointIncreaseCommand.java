package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public class PointIncreaseCommand implements WalletCommand{
    @Override
    public String execute(Wallet wallet, double amount) {
        wallet.updatePoint((int) amount);
        return "You receive " + amount + " points";
    }
}
