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
    public String pay(Wallet wallet, WalletCommand walletCommand, double amount) {
        if (wallet.getBalance() < amount) {
            this.status = false;
            return "Insufficient funds. Top up to pay. Current Balance: " + wallet.getBalance();
        } else {
            walletCommand.execute(wallet, amount);
            this.status = true;
            return "Your payment is succeed. Current Balance: " + wallet.getBalance();
        }
    }

    @Override
    public boolean isSucceed() {
        return this.status;
    }
}
