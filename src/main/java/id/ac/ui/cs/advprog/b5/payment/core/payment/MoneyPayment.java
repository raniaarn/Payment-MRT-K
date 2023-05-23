package id.ac.ui.cs.advprog.b5.payment.core.payment;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class MoneyPayment implements Payment{
    @Id
    @GeneratedValue
    private Integer id;

    private boolean status;

    @Override
    public Boolean pay(Wallet wallet, WalletCommand walletCommand, double amount) {
        if (wallet.getBalance() < amount) {
            return false;
        } else {
            walletCommand.execute(wallet, amount);
            return true;
        }
    }
}
