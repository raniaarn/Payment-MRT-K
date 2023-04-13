package id.ac.ui.cs.advprog.b5.payment.core;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    private Integer id;

    @JsonProperty(required = true)
    private double balance;

    @JsonProperty(value = "user", required = true)
    private Integer userId;

    //@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    //private List<CommandHistory> commandHistoryList;

    public Wallet(Integer userId) {
        this.userId = userId;
        this.id = userId;
    }

    public Wallet() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
