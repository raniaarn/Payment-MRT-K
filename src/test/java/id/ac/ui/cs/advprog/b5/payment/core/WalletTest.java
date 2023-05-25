package id.ac.ui.cs.advprog.b5.payment.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalletTest {
    @Test
    void testWalletProperties() {
        Wallet wallet = new Wallet();

        Integer expectedId = 110;
        wallet.setId(expectedId);
        wallet.setUserId(expectedId);
        double expectedAmount = 1000;
        wallet.setBalance(expectedAmount);

        Integer actualValue = wallet.getUserId();
        double actualNumber = wallet.getBalance();
        Integer actualId = wallet.getId();

        Assertions.assertEquals(expectedId, actualValue);
        Assertions.assertEquals(expectedId, actualId);
        Assertions.assertEquals(expectedAmount, actualNumber);
    }


    @Test
    void testToString() {
        Wallet wallet = new Wallet(110, 1000, 110);

        String expectedToString = "Wallet(id=110, balance=1000.0, userId=110)";
        String actualToString = wallet.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

    @Test
    void testHashCode() {
        Wallet wallet = new Wallet(110, 1000, 110);
        Wallet wallet2 = new Wallet(110, 1000, 110);
        Wallet wallet3 = new Wallet(100, 1200, 100);

        Assertions.assertNotEquals(wallet.hashCode(), wallet3.hashCode());
        Assertions.assertEquals(wallet.hashCode(), wallet.hashCode());
        Assertions.assertEquals(wallet.hashCode(), wallet2.hashCode());

        wallet.setBalance(2000);
        Assertions.assertNotEquals(wallet.hashCode(), wallet2.hashCode());
        Assertions.assertNotEquals(wallet.hashCode(), (Integer) null);
    }

    @Test
    void testEquals() {
        Wallet wallet = new Wallet(110, 1000, 110);
        Wallet wallet4 = wallet;
        Wallet wallet2 = new Wallet(110, 1000, 110);
        Wallet wallet3 = new Wallet(1110, 1020, 1110);
        UserWalletCommand command = new UserWalletCommand(110L, 110, "TOPUP 100");

        Assertions.assertEquals(true, wallet.equals(wallet));
        Assertions.assertEquals(true, wallet.equals(wallet4));
        Assertions.assertEquals(true, wallet.equals(wallet2));
        Assertions.assertNotEquals(true, wallet.equals(wallet3));
        Assertions.assertNotEquals(true, wallet.equals("someString"));
        Assertions.assertNotEquals(true, wallet.equals(null));
        Assertions.assertNotEquals(true, wallet.equals(command));

    }

    @Test
    void testToStringBuilder() {
        Wallet.WalletBuilder builder = new Wallet.WalletBuilder();
        builder.id(110);
        builder.userId(110);
        builder.balance(123);

        String expectedToString = "Wallet.WalletBuilder(id=110, balance=123.0, userId=110)";
        String actualToString = builder.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

}
