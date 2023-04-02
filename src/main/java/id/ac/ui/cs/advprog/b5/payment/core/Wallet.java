package id.ac.ui.cs.advprog.b5.payment.core;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Wallet {
    @JsonProperty(required = true)
    private double balance;

    @JsonProperty(required = true)
    private int point;

    @JsonProperty(value = "user", required = true)
    private String userId;

    public Wallet(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void updatePoint(int amount) {
        point += amount;
    }

    public int getPoint() {
        return point;
    }
}
