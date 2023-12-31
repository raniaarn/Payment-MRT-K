package id.ac.ui.cs.advprog.b5.payment.core.payment;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;

public interface Payment {
    Boolean pay(Wallet wallet, WalletCommand walletCommand, double amount);
}
