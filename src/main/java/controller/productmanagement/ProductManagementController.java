package controller.productmanagement;

import controller.adduser.AddUserController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetail;
import model.Product;
import util.CrudUtil;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ProductManagementController implements ProductManagementService {

    private static ProductManagementController instance;
    private ProductManagementController(){}
    public static ProductManagementController getInstance(){
        return instance==null?instance=new ProductManagementController():instance;
    }

    @Override
    public boolean addProduct(Product product) {
        String SQl = "INSERT INTO products values(?,?,?,?,?,?)";
        try {
            CrudUtil.execute(SQl,
                    product.getId(),
                    product.getCategory(),
                    product.getName(),
                    product.getPrice(),
                    product.getQtyOnHand(),
                    product.getSupplier()
            );
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        ObservableList<Product> itemObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM products";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()) {
                itemObservableList.add(new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String SQl = "UPDATE products set Category=?, Name=?, Price=?,QtyOnHand=?,supplierId=? WHERE productId=?";
        try {
            CrudUtil.execute(SQl,
                    product.getCategory(),
                    product.getName(),
                    product.getPrice(),
                    product.getQtyOnHand(),
                    product.getSupplier(),
                    product.getId()
            );
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProduct(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM products WHERE productId ='" + id + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product searchProduct(String name) {
        String SQL = "SELECT * FROM products WHERE name='" + name + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                return new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return null;
    }

    @Override
    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails){
            boolean isUpdateStock = updateStock(orderDetail);
            if (!isUpdateStock){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateStock(OrderDetail orderDetail) {
        String SQL = "Update products SET QtyOnHand=QtyOnHand-? WHERE name=?";
        try {
            return CrudUtil.execute(SQL,orderDetail.getQty(),orderDetail.getItemName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
