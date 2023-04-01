package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.TopUpCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.exceptions.InvalidUserException;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletAlreadyExistException;
import id.ac.ui.cs.advprog.b5.payment.repository.WalletRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class WalletServiceImpl implements WalletService {
    // top up certain amount, using buttons then add to user's wallet
    private final WalletRepository walletRepository;

    @Override
    public WalletRepository getWalletRepository() {
        return walletRepository;
    }

    @Override
    public Wallet getWallet(String userId) {
        return walletRepository.getWallet(userId);
    }

    @Override
    public Wallet createWallet(String userId) {
        if (walletRepository.getWalletMap().containsKey(userId)) {
            throw new WalletAlreadyExistException();
        }

        Wallet wallet = new Wallet(userId);
        walletRepository.addWallet(userId, wallet);
        return wallet;
    }
    @Override
    public Wallet topUp(TopUpRequest topUpRequest) {

        double amount = topUpRequest.getAmount();
        String userId = topUpRequest.getUserId();
        if (!walletRepository.getWalletMap().containsKey(userId)) {
            throw new InvalidUserException();
        }
        Wallet wallet = walletRepository.getWallet(userId);
        WalletCommand topup = new TopUpCommand();
        topup.execute(wallet, amount);
        return wallet;
    }
}
