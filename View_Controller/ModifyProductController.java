package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyProductController implements Initializable{

    Product newProduct;
    Product selectedProduct;
    int selectedIndex;
    
    @FXML
    private TextField AddProductIDTextField;

    @FXML
    private TextField AddNameIDTextField;

    @FXML
    private TextField AddInventoryTextField;

    @FXML
    private TextField AddPriceTextField;

    @FXML
    private TextField AddMaxTextField;

    @FXML
    private TextField AddMinTextField;

    @FXML
    private TextField SaveProductTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> AddPartID;

    @FXML
    private TableColumn<Part, String> AddPartName;

    @FXML
    private TableColumn<Part, Integer> AddInventoryLevel;

    @FXML
    private TableColumn<Part, Double> AddPriceperUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private TableView<Part> associatedPartTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartID;

    @FXML
    private TableColumn<Part, String> associatedPartName;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventory;

    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    @FXML
    private Button DeleteProduct;

    @FXML
    private Button SaveProduct;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SearchProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        updatePartTable();
        
        AddPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        AddPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        AddPriceperUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartTable.setItems(newProduct.getAssociatedParts());
        
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
             
    }
    
    @FXML
    void AddInventoryTextField(ActionEvent event) {

    }

    @FXML
    void AddMaxTextField(ActionEvent event) {

    }

    @FXML
    void AddMinTextField(ActionEvent event) {

    }

    @FXML
    void AddNameIDTextField(ActionEvent event) {

    }

    @FXML
    void AddPriceTextField(ActionEvent event) {

    }

    @FXML
    void AddProductHandler(ActionEvent event) {

        Part singlePart = partsTable.getSelectionModel().getSelectedItem();
        newProduct.setAssociatedParts(singlePart);
    }

    @FXML
    void AddProductIDTextField(ActionEvent event) {

    }

    @FXML
    void CancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field vlaues, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
          
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        }
    }

    @FXML
    void DeleteProductHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            newProduct.getAssociatedParts().remove(associatedPartTable.getSelectionModel().getSelectedItem());
        }
        
    }

    @FXML
    void SaveProductHandler(ActionEvent event) throws IOException {

       int id = selectedProduct.getProductID();
       String name = AddNameIDTextField.getText();
       int inventory = Integer.parseInt(AddInventoryTextField.getText());
       double price = Double.parseDouble(AddPriceTextField.getText());
       int max = Integer.parseInt(AddMaxTextField.getText());
       int min = Integer.parseInt(AddMinTextField.getText());
       

       
       Product modifyProduct = new Product(id, name, price, inventory, min, max);
       Inventory.getAllProducts().set(selectedIndex, modifyProduct);
        
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
       stage.setScene(new Scene((Parent) scene));
       stage.show();
    }

    @FXML
    void SaveProductTextField(ActionEvent event) {

    }

    @FXML
    void searchProductHandler(ActionEvent event) {
        
        String x = SaveProductTextField.getText();
        partsTable.getSelectionModel().select(Part.searchPart(x));
    }
    
    public void setProduct(Product product, int index) {
        selectedProduct = product;
        selectedIndex = index;
        
    if (product instanceof Product) {
            Product newProduct = (Product) product;

            this.AddNameIDTextField.setText(newProduct.getName());
            this.AddInventoryTextField.setText((Integer.toString(newProduct.getStock())));
            this.AddPriceTextField.setText((Double.toString(newProduct.getPrice())));
            this.AddMaxTextField.setText((Integer.toString(newProduct.getMin())));
            this.AddMinTextField.setText((Integer.toString(newProduct.getMax())));
        
        }    
    }
    
    public void updatePartTable() {
        partsTable.setItems(Inventory.getAllParts());
        
    }

}

