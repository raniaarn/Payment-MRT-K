package id.ac.ui.cs.advprog.b5.payment.controller;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/all")
    public ResponseEntity<List<Wallet>> getAll() {
        List<Wallet> response = walletService.getAllWallet();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<UserWalletCommand>> getAllById(@PathVariable Integer userId) {
        List<UserWalletCommand> response = walletService.getUserHistory(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/topup")
    public ResponseEntity<Wallet> topUpToWallet (@RequestBody TopUpRequest topUpRequest) {
        Wallet response = walletService.topUp(topUpRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getWallet(@PathVariable Integer userId) {
        Double response = walletService.getWallet(userId).getBalance();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-wallet")
    public ResponseEntity createWallet (@RequestBody Integer userId) {
        Wallet response = walletService.createWallet(userId);
        return ResponseEntity.ok(response);
    }
}
