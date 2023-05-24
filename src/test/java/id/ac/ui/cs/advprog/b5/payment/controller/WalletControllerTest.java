package id.ac.ui.cs.advprog.b5.payment.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import id.ac.ui.cs.advprog.b5.payment.dto.PaymentRequest;
import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
import id.ac.ui.cs.advprog.b5.payment.service.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletController walletController;

    @MockBean
    private WalletServiceImpl service;

    Wallet wallet;

    Wallet wallet2;
    Integer userId;
    Integer userId2;
    List<Wallet> listWallet;
    List<UserWalletCommand> listCommand;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userId = 1;
        userId2 = 2;

        wallet = Wallet.builder()
                .userId(userId)
                .balance(0)
                .build();

        wallet2 = Wallet.builder()
                .userId(userId2)
                .balance(0)
                .build();

        listWallet = new ArrayList<>();
        listWallet.add(wallet);
        listWallet.add(wallet2);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        lenient().when(service.getWallet(1)).thenReturn(wallet);
        lenient().when(service.getAllWallet()).thenReturn(listWallet);
    }

    @Test
    void testGetAllWallet() throws Exception {
        var expectedResponseContent = "[{\"id\":null,\"balance\":0.0,\"user\":1},{\"id\":null,\"balance\":0.0,\"user\":2}]";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/payment/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponseContent));
    }

    @Test
    void testGetWalletById() throws Exception {
        var expectedResponseContent = 0.0;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/payment/balance/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedResponseContent)));
    }

    @Test
    void testCreateWallet() throws Exception {
        var expectedResponseContent = "{\"id\":null,\"balance\":0.0,\"user\":1}";
        when(service.createWallet(any(Integer.class))).thenReturn(wallet);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payment/create-wallet")
                .content(String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponseContent));
    }

    @Test
    void testTopUp() throws Exception {
        var expectedResponseContent = "{\"id\":null,\"balance\":0.0,\"user\":1}";

        TopUpRequest topUpRequest = new TopUpRequest();

        when(service.topUp(any(TopUpRequest.class))).thenReturn(wallet);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/payment/topup")
                        .content(objectMapper.writeValueAsString(topUpRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponseContent));
    }

    @Test
    void testPayment() throws Exception {
        var expectedResponseContent = "true";
        PaymentRequest paymentRequest = new PaymentRequest();

        when(service.pay(any(PaymentRequest.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/payment/pay")
                        .content(objectMapper.writeValueAsString(paymentRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponseContent));
    }

    @Test
    void testGetHistoryUser() throws Exception {
        UserWalletCommand userWalletCommand = new UserWalletCommand();
        userWalletCommand.setUserId(1);

        when(service.getUserHistory(any(Integer.class))).thenReturn(List.of(userWalletCommand));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/payment/history/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("getAllById"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(String.valueOf(userWalletCommand.getUserId())));

    }
}

