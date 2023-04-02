//package id.ac.ui.cs.advprog.b5.payment.controller;
//import id.ac.ui.cs.advprog.b5.payment.PaymentApplication;
//import id.ac.ui.cs.advprog.b5.payment.Util;
//import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
//import id.ac.ui.cs.advprog.b5.payment.dto.TopUpRequest;
//import id.ac.ui.cs.advprog.b5.payment.service.WalletService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//@Import(WalletController.class)
//@SpringBootTest(classes = PaymentApplication.class)
//@WebAppConfiguration
//public class WalletControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    @MockBean
//    private WalletService walletService;
//
//    private TopUpRequest test;
//
//    private Wallet walletB;
//
//    @BeforeEach
//    protected void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void createWallet() throws Exception {
//        String uri = "/api/payment/create-wallet";
//        Wallet wallet = new Wallet("2106650222");
//        String inputJson = Util.mapToJson(wallet);
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//    }
//
//}