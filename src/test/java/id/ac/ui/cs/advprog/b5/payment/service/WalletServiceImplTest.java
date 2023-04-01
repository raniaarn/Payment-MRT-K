package id.ac.ui.cs.advprog.b5.payment.service;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.exceptions.InvalidUserException;
import id.ac.ui.cs.advprog.b5.payment.exceptions.WalletAlreadyExistException;
import id.ac.ui.cs.advprog.b5.payment.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletServiceImplTest {
    @InjectMocks
    private WalletServiceImpl service;

    @Mock
    private WalletRepository walletRepository;

    String userId1;
    Wallet walletA;

    @BeforeEach
    void setUp() {
        walletRepository = new WalletRepository();
        service = new WalletServiceImpl(walletRepository);

        userId1 = "2106650222";

        walletA = service.createWallet(userId1);
    }


    // positive testing
    @Test
    void whenFindWalletByIdAndShouldReturnWalletDetails() {
        var result = service.getWallet(userId1);

        var expected = new Wallet(userId1);

        assertEquals(result, expected);
    }

    @Test
    void whenTopUpAndShouldReturnBalance() {
        TopUpRequest topUpRequest = new TopUpRequest();
        topUpRequest.setAmount(50000.0);
        topUpRequest.setUserId(userId1);
        service.topUp(topUpRequest);
        topUpRequest.setAmount(15000.0);
        var result = service.topUp(topUpRequest).getBalance();

        var expected = 65000.0;

        assertEquals(result, -expected);
    }

    // negative testing
    @Test
    void createWalletForSameUserAndShouldBeError() {
        boolean thrown = false;

        try {
            Wallet walletB = service.createWallet(userId1);
        } catch (WalletAlreadyExistException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void whenFindByIdWithWrongUserId() {
        boolean thrown = false;

        try {
            TopUpRequest topUpRequest = new TopUpRequest();
            topUpRequest.setAmount(50000.0);
            topUpRequest.setUserId("2392392");
            service.topUp(topUpRequest);
        } catch (InvalidUserException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
