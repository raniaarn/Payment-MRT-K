package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.TopUpCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletDoesNotExistException;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletAlreadyExistException;
import id.ac.ui.cs.advprog.b5.payment.repository.WalletRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class WalletServiceImpl implements WalletService {
    // top up certain amount, using buttons then add to user's wallet
    private final WalletRepository walletRepository;

    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    }
    @Override
    public Wallet getWallet(Integer userId) {
        if (walletRepository.findById(userId).isEmpty()) {
            throw new WalletDoesNotExistException();
        }
        return walletRepository.findById(userId).get();
    }

    @Override
    public Wallet createWallet(Integer userId) {
        if (walletRepository.findById(userId).isPresent()) {
            throw new WalletAlreadyExistException();
        }

        Wallet wallet = new Wallet(userId);
        walletRepository.save(wallet);
        return wallet;
    }
    @Override
    public Wallet topUp(TopUpRequest topUpRequest) {

        double amount = topUpRequest.getAmount();
        Integer userId = topUpRequest.getUserId();
        if (walletRepository.findById(userId).isEmpty()) {
            throw new WalletDoesNotExistException();
        }
        Wallet wallet = walletRepository.findById(userId).get();
        WalletCommand topUpCommand = new TopUpCommand();
        topUpCommand.execute(wallet, amount);
        walletRepository.save(wallet);
        return wallet;
    }
}
