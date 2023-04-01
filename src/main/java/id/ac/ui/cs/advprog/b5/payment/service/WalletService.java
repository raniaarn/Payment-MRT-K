package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.repository.WalletRepository;

public interface WalletService {
    // top up certain amount, using buttons then add to user's wallet
    public Wallet createWallet(String userId);
    public Wallet topUp(TopUpRequest topUpRequest);
    public WalletRepository getWalletRepository();
    public Wallet getWallet(String userId);
    }
