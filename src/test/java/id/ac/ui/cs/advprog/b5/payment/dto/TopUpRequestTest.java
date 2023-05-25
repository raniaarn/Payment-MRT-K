package id.ac.ui.cs.advprog.b5.payment.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TopUpRequestTest {
    @Test
    void testDtoProperties() {
        TopUpRequest dto = new TopUpRequest();

        Integer expectedId = 110;
        dto.setUserId(expectedId);
        double expectedAmount = 1000;
        dto.setAmount(expectedAmount);

        Integer actualValue = dto.getUserId();
        double actualNumber = dto.getAmount();

        Assertions.assertEquals(expectedId, actualValue);
        Assertions.assertEquals(expectedAmount, actualNumber);
    }

    @Test
    void testToString() {
        TopUpRequest dto = new TopUpRequest();
        dto.setUserId(110);
        dto.setAmount(100);

        String expectedToString = "TopUpRequest(userId=110, amount=100.0)";
        String actualToString = dto.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

    @Test
    void testHashCode() {
        TopUpRequest dto1 = new TopUpRequest();
        dto1.setUserId(100);
        dto1.setAmount(100);

        TopUpRequest dto2 = new TopUpRequest();
        dto2.setUserId(100);
        dto2.setAmount(100);

        TopUpRequest dto3 = new TopUpRequest();
        dto3.setUserId(120);
        dto3.setAmount(45);

        Assertions.assertEquals(dto1.hashCode(), dto2.hashCode());
        Assertions.assertEquals(dto1.hashCode(), dto1.hashCode());
        Assertions.assertNotEquals(dto1.hashCode(), dto3.hashCode());

        dto1.setAmount(200);
        Assertions.assertNotEquals(dto1.hashCode(), dto2.hashCode());
        Assertions.assertNotEquals(dto1.hashCode(), (Integer) null);

    }

    @Test
    void testEquals() {
        TopUpRequest dto1 = new TopUpRequest();
        dto1.setUserId(100);
        dto1.setAmount(100);

        TopUpRequest dto2 = new TopUpRequest();
        dto2.setUserId(100);
        dto2.setAmount(100);

        TopUpRequest dto3 = new TopUpRequest();
        dto3.setUserId(120);
        dto3.setAmount(45);

        PaymentRequest dto4 = new PaymentRequest();
        dto3.setUserId(120);
        dto3.setAmount(45);

        Assertions.assertEquals(true, dto1.equals(dto2));
        Assertions.assertNotEquals(true, dto1.equals(dto3));
        Assertions.assertEquals(true, dto1.equals(dto1));
        Assertions.assertNotEquals(true, dto1.equals("test"));
        Assertions.assertNotEquals(true, dto1.equals(null));
        Assertions.assertNotEquals(true, dto1.equals(dto4));

    }

    @Test
    void testToStringBuilder() {
        TopUpRequest.TopUpRequestBuilder builder = new TopUpRequest.TopUpRequestBuilder();
        builder.userId(110);
        builder.amount(123);

        String expectedToString = "TopUpRequest.TopUpRequestBuilder(userId=110, amount=123.0)";
        String actualToString = builder.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}

