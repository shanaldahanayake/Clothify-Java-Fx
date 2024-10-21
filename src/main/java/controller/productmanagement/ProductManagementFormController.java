package controller.productmanagement;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Product;

public class ProductManagementFormController {

    @FXML
    private ComboBox<?> cmbCategory;

    @FXML
    private ComboBox<?> cmbSupplierName;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPirce;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableView<?> tblCustomers;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product customer = new Product(
                Integer.parseInt(txtProductId.getText()),
                txtProductName.getText(),
                (String) cmbSupplierName.getValue(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                (String) cmbCategory.getValue()

        );
        if (true) {
            new Alert(Alert.AlertType.INFORMATION).show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
