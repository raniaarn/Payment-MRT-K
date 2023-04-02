package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public class PointDeductCommand implements WalletCommand{
    @Override
    public String execute(Wallet wallet, double amount) {
        wallet.updatePoint(-1* ((int) amount));
        return "You used " + amount + " points";
    }
}
