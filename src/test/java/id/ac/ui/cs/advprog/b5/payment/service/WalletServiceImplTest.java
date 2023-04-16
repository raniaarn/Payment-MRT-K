package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.core.command.WalletCommand;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {
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
        when(commandRepository.findAllByUserId(any(Integer.class))).thenReturn(List.of(any(UserWalletCommand.class)));

        Double result = service.topUp(topUpRequest).getBalance();
        Assertions.assertEquals(50000, result);
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
