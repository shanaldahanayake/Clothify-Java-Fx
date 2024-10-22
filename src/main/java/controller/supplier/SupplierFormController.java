package controller.supplier;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.Supplier;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> tblSuppliers;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    SupplierService service=SupplierController.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                addValueToText(newVal);
            }
        });

        loadTable();
    }
    private void loadTable() {
        tblSuppliers.setItems(service.getAllSuppliers());
    }

    private void addValueToText(Supplier newVal) {
        txtSupplierId.setText(newVal.getId());
        txtSupplierName.setText(newVal.getName());
        txtCompany.setText(newVal.getCompany());
        txtEmail.setText(newVal.getEmail());
    }
}
