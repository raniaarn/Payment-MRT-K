package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public interface WalletCommand {
    String execute(Wallet wallet, double amount);
}
