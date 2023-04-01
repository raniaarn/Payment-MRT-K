package id.ac.ui.cs.advprog.b5.payment.core;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wallet {
    @JsonProperty(required = true)
    private int balance;

    @JsonProperty(required = true)
    private int point;

    @JsonProperty(value = "user", required = true)
    private String userId;

    public Wallet(String userId) {
        this.userId = userId;
    }
    public String addToBalance(double amount) {
        balance += amount;
        return "Your current point is " + balance;
    }

    public String getUserId() {
        return userId;
    }

    public String reduceBalance(double amount) {
        balance -= amount;
        return "Your current point is " + balance;
    }

    public double getBalance() {
        return balance;
    }

    public String addPoint(int amount) {
        return "0";
    }

    public String reducePoint(int amount) {
        return "0";
    }

    public int getPoint() {
        return point;
    }
}
