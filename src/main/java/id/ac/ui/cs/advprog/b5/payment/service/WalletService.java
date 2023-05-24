package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import java.util.List;

public interface WalletService {
    // top up certain amount, using buttons then add to user's wallet
    public List<Wallet> getAllWallet();
    public Wallet createWallet(Integer userId);
    public Wallet topUp(TopUpRequest topUpRequest);
    public Wallet getWallet(Integer userId);
    public List<UserWalletCommand> getUserHistory(Integer userId);
    public Boolean pay(PaymentRequest paymentRequest);
    }
