package id.ac.ui.cs.advprog.b5.payment.core.command;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;

public interface WalletCommand {
    public abstract String execute(Wallet wallet, double amount);
    public abstract String getName();
}
