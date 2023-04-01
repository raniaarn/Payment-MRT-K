package id.ac.ui.cs.advprog.b5.payment.controller;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PutMapping("/topup")
    public ResponseEntity<Wallet> topUpToWallet (@RequestBody TopUpRequest topUpRequest) {
        Wallet response = walletService.topUp(topUpRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String userId) {
        Wallet response = walletService.getWallet(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-wallet")
    public ResponseEntity createWallet (@RequestBody String userId) {
        Wallet response = walletService.createWallet(userId);
        return ResponseEntity.ok(response);
    }
}
