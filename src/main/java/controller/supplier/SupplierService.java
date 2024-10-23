package controller.supplier;

import javafx.collections.ObservableList;
import model.Product;
import model.Supplier;

public interface SupplierService {
    boolean addSupplier(Supplier supplier);
    ObservableList<Supplier> getAllSuppliers();
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
}
