package controller.productmanagement;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductManagementFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPirce;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableView<Product> tblProducts;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQtyOnHand;

    ProductManagementService service = ProductManagementController.getInstance();
    @FXML
    void btnAddOnAction(ActionEvent event) {
        Product product = new Product(
                txtProductId.getText(),
                cmbCategory.getValue(),
                txtProductName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                cmbSupplierId.getValue()
        );
        if (service.addProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION,"Product Added Successfully");
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (service.deleteProduct(txtProductId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Product Deleted!!").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Product Not Deleted!!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Product product = new Product(
                txtProductId.getText(),
                cmbCategory.getValue(),
                txtProductName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                cmbSupplierId.getValue()
        );
        if (service.updateProduct(product)) {
            new Alert(Alert.AlertType.INFORMATION,"Product Updated Successfully").show();
            loadTable();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPirce.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));


        tblProducts.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });

        loadTable();
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("Blouse");
        categoryList.add("Jeans");
        categoryList.add("Saree");
        categoryList.add("Shirt");
        categoryList.add("T-Shirt");
        categoryList.add("Trousers");
        cmbCategory.setItems(categoryList);

        ObservableList<String> supplierList = FXCollections.observableArrayList();
    }

    private void addValueToText(Product newVal) {
        txtProductId.setText(newVal.getId());
        txtProductName.setText(newVal.getName());
        txtPrice.setText(newVal.getPrice().toString());
        txtQtyOnHand.setText(newVal.getQtyOnHand().toString());
        cmbCategory.setValue(newVal.getCategory());
        cmbSupplierId.setValue(newVal.getSupplier());
    }


    private void loadTable() {
        tblProducts.setItems(service.getAllProducts());
    }
}
