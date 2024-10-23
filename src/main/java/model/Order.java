package model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @NonNull
    private Integer orderId;
    private LocalDate date;
    private String customerName;
    private List<OrderDetail> orderDetails;

}
