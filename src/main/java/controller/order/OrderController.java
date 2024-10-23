package controller.order;

import controller.productmanagement.ProductManagementController;
import db.DBConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Order;
import model.Supplier;
import util.CrudUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderController{

    private static OrderController instance;
    private OrderController(){}
    public static OrderController getInstance(){
        return instance==null?instance=new OrderController():instance;
    }


    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String SQL = "INSERT INTO orders VALUES(?,?,?)";
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, order.getOrderId());
            psTm.setObject(2, order.getDate());
            psTm.setObject(3, order.getCustomerName());
            boolean isOrderAdd = psTm.executeUpdate() > 0;
            if (isOrderAdd) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd) {
                    boolean isUpdateStock = ProductManagementController.getInstance().updateStock(order.getOrderDetails());
                    if (isUpdateStock) {
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Order Placed!!").show();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public Order getOrder(){
        String SQL = "SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()) {
             return new Order(
                        resultSet.getInt(1)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
