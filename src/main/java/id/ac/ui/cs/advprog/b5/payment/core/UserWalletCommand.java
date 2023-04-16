package id.ac.ui.cs.advprog.b5.payment.core;

import id.ac.ui.cs.advprog.b5.payment.core.command.TopUpCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.payment.MoneyPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "commands")
public class UserWalletCommand {

    @Id
    @GeneratedValue
    private Long id;

    private Integer userId;

    private String commandName;

    public UserWalletCommand() {

    }
}
