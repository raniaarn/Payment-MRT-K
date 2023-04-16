package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.DeductCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.TopUpCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.payment.MoneyPayment;
import id.ac.ui.cs.advprog.b5.payment.core.payment.Payment;
import id.ac.ui.cs.advprog.b5.payment.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletDoesNotExistException;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletAlreadyExistException;
import id.ac.ui.cs.advprog.b5.payment.repository.CommandRepository;
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
    private final CommandRepository commandRepository;

    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    }

    @Override
    public List<UserWalletCommand> getUserHistory(Integer userId) {
        return commandRepository.findAllByUserId(userId);
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

        var wallet = Wallet.builder().id(userId).userId(userId).build();
        walletRepository.save(wallet);
        return wallet;
    }
    @Override
    public Wallet topUp(TopUpRequest topUpRequest) {

        // check
        double amount = topUpRequest.getAmount();
        Integer userId = topUpRequest.getUserId();
        if (walletRepository.findById(userId).isEmpty()) {
            throw new WalletDoesNotExistException();
        }

        // get wallet
        Wallet wallet = walletRepository.findById(userId).get();
        WalletCommand topUpCommand = new TopUpCommand();
        topUpCommand.execute(wallet, amount);

        // save to history
        UserWalletCommand newCommand = UserWalletCommand.builder()
                .userId(userId)
                .commandName(topUpCommand.getName())
                .build();

        // save to repo
        walletRepository.save(wallet);
        commandRepository.save(newCommand);
        return wallet;
    }

    @Override
    public String pay(PaymentRequest paymentRequest) {
        Payment payment = new MoneyPayment();

        // check
        double amount = paymentRequest.getAmount();
        Integer userId = paymentRequest.getUserId();
        if (walletRepository.findById(userId).isEmpty()) {
            throw new WalletDoesNotExistException();
        }

        // get wallet
        Wallet wallet = walletRepository.findById(userId).get();
        WalletCommand command = new DeductCommand();
        String pay = payment.pay(wallet, command, amount);

        // save to history if succeed
        if (payment.isSucceed()) {
            UserWalletCommand newCommand = UserWalletCommand.builder()
                    .userId(userId)
                    .commandName(command.getName())
                    .build();

            commandRepository.save(newCommand);
        }

        // save to repo
        walletRepository.save(wallet);
        return pay;
    }


}
