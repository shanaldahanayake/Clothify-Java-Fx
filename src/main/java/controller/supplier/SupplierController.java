package controller.supplier;

import controller.productmanagement.ProductManagementController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.Supplier;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController implements SupplierService{
    private static SupplierController instance;
    private SupplierController(){}
    public static SupplierController getInstance(){
        return instance==null?instance=new SupplierController():instance;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String SQl = "INSERT INTO supplier values(?,?,?,?)";
        try {
            CrudUtil.execute(SQl,
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getEmail()
            );
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()) {
                supplierObservableList.add(new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        String SQl = "UPDATE supplier set supplierName=?, supplierCompany=?, supplierEmail=? WHERE supplierId=?";
        try {
            CrudUtil.execute(SQl,
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getEmail(),
                    supplier.getId()
            );
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM supplier WHERE supplierId ='" + id + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
