package id.ac.ui.cs.advprog.b5.payment.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequest {
    private Integer userId;
    private double amount;
}
