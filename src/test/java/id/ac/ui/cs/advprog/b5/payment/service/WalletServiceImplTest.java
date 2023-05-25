package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.DeductCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.TopUpCommand;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
import id.ac.ui.cs.advprog.b5.payment.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletDoesNotExistException;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletAlreadyExistException;
import id.ac.ui.cs.advprog.b5.payment.repository.CommandRepository;
import id.ac.ui.cs.advprog.b5.payment.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {
    @InjectMocks
    private WalletServiceImpl service;

    @Mock
    private WalletRepository repository;

    @Mock
    private CommandRepository commandRepository;

    Integer userId1;
    Wallet walletA;
    Wallet walletB;

    TopUpRequest topUpRequest;

    PaymentRequest paymentRequest;

    PaymentRequest paymentRequestFail;

    @BeforeEach
    void setUp() {
        userId1 = 2106650222;

        walletA = Wallet.builder()
                .userId(userId1)
                .balance(0)
                .build();

        topUpRequest = TopUpRequest.builder()
                .amount(50000)
                .userId(userId1)
                .build();

        paymentRequest = PaymentRequest.builder()
                .amount(2000)
                .userId(userId1)
                .build();

        paymentRequestFail = PaymentRequest.builder()
                .amount(100000)
                .userId(userId1)
                .build();

        walletB = Wallet.builder()
                .balance(50000)
                .build();
    }


    // positive testing
    @Test
    void whenFindWalletByIdAndShouldReturnWalletDetails() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        Wallet result = service.getWallet(2106650222);
        verify(repository, atLeastOnce()).findById(any(Integer.class));
        assertEquals(walletA, result);
    }

    @Test
    void whenTopUpAndShouldReturnBalance() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        Double result = service.topUp(topUpRequest).getBalance();
        Assertions.assertEquals(50000, result);
    }

    @Test
    void whenPayAndShouldReturnTrue() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        service.topUp(topUpRequest);

        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        Boolean result = service.pay(paymentRequest);
        Assertions.assertEquals(true, result);
    }

    @Test
    void whenPayAndShouldReturnFalse() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        Boolean result = service.pay(paymentRequestFail);
        Assertions.assertEquals(false, result);
    }

    @Test
    void whenGetHistoryReturnHistory() {
        List<UserWalletCommand> listCommand = new ArrayList<>();
        WalletCommand command1 = new TopUpCommand();
        command1.execute(walletA, 50000);
        UserWalletCommand userCommand1 = new UserWalletCommand(1L, userId1, command1.getName());
        WalletCommand command2  = new DeductCommand();
        command2.execute(walletA, 2000);
        UserWalletCommand userCommand2 = new UserWalletCommand(2L, userId1, command2.getName());
        listCommand.add(userCommand1);
        listCommand.add(userCommand2);

        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));
        when(commandRepository.findAllByUserId(any(Integer.class))).thenReturn(listCommand);
        service.topUp(topUpRequest);
        service.pay(paymentRequest);

        List<UserWalletCommand> result = service.getUserHistory(userId1);
        Assertions.assertEquals(listCommand, result);
    }

    @Test
    void whenGetAllIdShouldReturnWallets() {
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(walletA);
        wallets.add(walletB);

        when(repository.findAll()).thenReturn(wallets);
        List<Wallet> result = service.getAllWallet();
        Assertions.assertEquals(wallets, result);
    }

    @Test
    void whenCreateWalletShouldReturnWallet() {
        Wallet wallet = new Wallet(110, 0, 110);

        when(repository.save(any(Wallet.class))).thenReturn(wallet);
        Wallet result = service.createWallet(110);

        Assertions.assertEquals(wallet, result);
    }


    // negative testing
    @Test
    void createWalletForSameUserAndShouldBeError() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(walletA));

        Assertions.assertThrows(WalletAlreadyExistException.class, () -> {
            service.createWallet(2106650222);
        });

    }

    @Test
    void whenFindByIdWithWrongUserId() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(WalletDoesNotExistException.class, () -> {
            service.getWallet(0);
        });
    }
}
