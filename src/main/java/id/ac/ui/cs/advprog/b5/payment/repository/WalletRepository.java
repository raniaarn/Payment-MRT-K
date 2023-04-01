package id.ac.ui.cs.advprog.b5.payment.repository;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WalletRepository {
    private final Map<String, Wallet> walletMap;

    public WalletRepository() {
        this.walletMap = new HashMap<>();
    }

    public void addWallet(String userId, Wallet wallet) {
        walletMap.put(userId, wallet);
    }

    public Wallet getWallet(String userId) {
        return walletMap.get(userId);
    }

    public Map<String, Wallet> getWalletMap() {
        return walletMap;
    }
}
