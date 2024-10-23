package controller.order;

import com.jfoenix.controls.JFXTextField;
import controller.productmanagement.ProductManagementController;
import controller.productmanagement.ProductManagementService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable{

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private ComboBox<String> cmbPayment;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TableView<Cart> tblCart;

    @FXML
    private JFXTextField txtCustomerF;

    @FXML
    private JFXTextField txtCustomerL;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtPrice;

    ObservableList<Cart> cart = FXCollections.observableArrayList();
    ProductManagementService productManagementService=ProductManagementController.getInstance();
    @FXML
    void btnAddtoCartOnAction(ActionEvent event) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemCode = cmbItemCode.getValue();
        String itemDesc = txtItemName.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double unitPrice = Double.valueOf(txtPrice.getText());
        Double total = unitPrice * qty;

        Product product = productManagementService.searchProduct(itemDesc);

        if (product.getQtyOnHand()<qty) {
            new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
        } else {
            cart.add(new Cart(itemCode, itemDesc, qty, unitPrice, total));
            tblCart.setItems(cart);
            calcTotal();
        }
    }

    private void calcTotal(){
        Double netTotal=0.0;
        for (Cart carts : cart){
            netTotal += carts.getTotal();
        }
        lblNetTotal.setText(netTotal.toString());
    }

    private void loadDateAndTime () {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblOrderTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadProductCodes () {
        ObservableList<String> idsList = FXCollections.observableArrayList();
        ObservableList<Product> allItems = ProductManagementController.getInstance().getAllProducts();

        allItems.forEach(obj -> {
            idsList.add(obj.getName());
        });

        cmbItemCode.setItems(idsList);

    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        Integer orderId = Integer.parseInt(lblOrderId.getText());
        LocalDate date = LocalDate.parse(lblOrderDate.getText());
        String cusName=txtCustomerF.getText();
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        cart.forEach(obj->{
            orderDetails.add(
                    new OrderDetail(
                            lblOrderId.getText(),
                            obj.getItemCode(),
                            obj.getQty()
                    )
            );
        });
        Order order = new Order(orderId, date,cusName ,orderDetails);
        try {
            OrderController.getInstance().placeOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadProductCodes();
        lblOrderId.setText(Integer.toString(OrderController.getInstance().getOrder().getOrderId()+1));

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            System.out.println(t1);
            if (t1 != null) {
                searchItem(t1);
            }
        });

        ObservableList<String> paymentMethod = FXCollections.observableArrayList();
        paymentMethod.add("Cash");
        paymentMethod.add("Debit/Credit Card");
        cmbPayment.setItems(paymentMethod);
    }
    private void searchItem(String name) {
        Product product = ProductManagementController.getInstance().searchProduct(name);
        txtItemName.setText(product.getName());
        txtPrice.setText(product.getPrice().toString());
    }
}











