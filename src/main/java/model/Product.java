package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String category;
    private String name;
    private Double price;
    private Integer qtyOnHand;
    private String supplier;
}
