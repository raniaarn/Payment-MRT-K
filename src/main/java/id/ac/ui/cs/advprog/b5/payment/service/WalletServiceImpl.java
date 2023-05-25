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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final CommandRepository commandRepository;

    public Optional<WalletRepository> getWalletRepository() {
        return Optional.ofNullable(this.walletRepository);
    }

    public Optional<CommandRepository> getCommandRepository() {
        return Optional.ofNullable(commandRepository);
    }

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
        Optional<Wallet> walletOptional = walletRepository.findById(userId);
        if (walletOptional.isEmpty()) {
            throw new WalletDoesNotExistException();
        } else {
            return walletOptional.get();
        }
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
        double amount = topUpRequest.getAmount();
        Integer userId = topUpRequest.getUserId();

        var wallet = getWallet(userId);
        WalletCommand topUpCommand = new TopUpCommand();
        topUpCommand.execute(wallet, amount);

        saveToHistory(userId, topUpCommand);
        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public Boolean pay(PaymentRequest paymentRequest) {
        Payment payment = new MoneyPayment();
        double amount = paymentRequest.getAmount();
        Integer userId = paymentRequest.getUserId();

        var wallet = getWallet(userId);
        WalletCommand command = new DeductCommand();
        Boolean pay = payment.pay(wallet, command, amount);

        // save to history if succeed
        if (Boolean.TRUE.equals(pay)) {
            saveToHistory(userId, command);
        }

        walletRepository.save(wallet);
        return pay;
    }

    private void saveToHistory(Integer userId, WalletCommand command) {
        UserWalletCommand newCommand = UserWalletCommand.builder()
                .userId(userId)
                .commandName(command.getName())
                .build();

        commandRepository.save(newCommand);
    }
}
