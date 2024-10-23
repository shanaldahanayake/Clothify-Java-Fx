package controller.productmanagement;

import javafx.collections.ObservableList;
import model.OrderDetail;
import model.Product;

import java.util.List;

public interface ProductManagementService {
    boolean addProduct(Product product);
    ObservableList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProduct(String id);
    Product searchProduct(String name);
    boolean updateStock(List<OrderDetail> orderDetails);
    boolean updateStock(OrderDetail orderDetails);
}
