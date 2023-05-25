package id.ac.ui.cs.advprog.b5.payment.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentRequestTest {
    @Test
    void testDtoProperties() {
        PaymentRequest dto = new PaymentRequest();

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
        PaymentRequest dto = new PaymentRequest();
        dto.setUserId(110);
        dto.setAmount(100);

        String expectedToString = "PaymentRequest(userId=110, amount=100.0)";
        String actualToString = dto.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

    @Test
    void testHashCode() {
        PaymentRequest dto1 = new PaymentRequest();
        dto1.setUserId(100);
        dto1.setAmount(100);

        PaymentRequest dto2 = new PaymentRequest();
        dto2.setUserId(100);
        dto2.setAmount(100);

        PaymentRequest dto3 = new PaymentRequest();
        dto3.setUserId(120);
        dto3.setAmount(45);

        Assertions.assertEquals(dto1.hashCode(), dto1.hashCode());
        Assertions.assertEquals(dto1.hashCode(), dto2.hashCode());
        Assertions.assertNotEquals(dto1.hashCode(), dto3.hashCode());

        dto1.setAmount(200);
        Assertions.assertNotEquals(dto1.hashCode(), dto2.hashCode());
        Assertions.assertNotEquals(dto1.hashCode(), (Integer) null);
    }

    @Test
    void testEquals() {
        PaymentRequest dto1 = new PaymentRequest();
        dto1.setUserId(100);
        dto1.setAmount(100);

        PaymentRequest dto2 = new PaymentRequest();
        dto2.setUserId(100);
        dto2.setAmount(100);

        PaymentRequest dto3 = new PaymentRequest();
        dto3.setUserId(120);
        dto3.setAmount(45);

        Assertions.assertEquals(true, dto1.equals(dto1));
        Assertions.assertEquals(true, dto1.equals(dto2));
        Assertions.assertNotEquals(true, dto1.equals(dto3));
        Assertions.assertNotEquals(true, dto1.equals("test"));
        Assertions.assertNotEquals(true, dto1.equals(null));


    }

    @Test
    void testToStringBuilder() {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.userId(110);
        builder.amount(123);

        String expectedToString = "PaymentRequest.PaymentRequestBuilder(userId=110, amount=123.0)";
        String actualToString = builder.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
}

