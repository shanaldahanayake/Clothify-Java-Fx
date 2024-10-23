package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String itemCode;
    private String itemName;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
