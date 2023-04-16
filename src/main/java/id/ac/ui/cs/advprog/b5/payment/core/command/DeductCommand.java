package id.ac.ui.cs.advprog.b5.payment.core.command;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class DeductCommand implements WalletCommand {
    private double amount;

    @Override
    public String execute(Wallet wallet, double amount) {
        wallet.updateBalance(-amount);
        this.amount = amount;
        return "your balance is now " + wallet.getBalance();
    }

    @Override
    public String getName() {
        return "DEDUCT " + amount;
    }
}
