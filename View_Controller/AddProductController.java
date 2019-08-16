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

public class AddProductController implements Initializable{

    Product newProduct;
    
    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField AddPriceTextField;

    @FXML
    private TextField MaxAddPartText;

    @FXML
    private TextField MinAddPartText;

    @FXML
    private TextField SaveProductTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PriceCostPerUnit;

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
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartTable.setItems(newProduct.getAssociatedParts());
        
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
        
    }
    
    @FXML
    void AddPriceTextField(ActionEvent event) {

    }

    @FXML
    void AddProductHandler(ActionEvent event) throws IOException {
  
        Part singlePart = partsTable.getSelectionModel().getSelectedItem();
        newProduct.setAssociatedParts(singlePart);
       
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
    void IDAddPartText(ActionEvent event) {

    }

    @FXML
    void InventoryAddPartText(ActionEvent event) {

    }

    @FXML
    void MaxAddPartText(ActionEvent event) {

    }

    @FXML
    void MinAddPartText(ActionEvent event) {

    }

    @FXML
    void NameAddPartText(ActionEvent event) {

    }

    @FXML
    void SaveProductHandler(ActionEvent event) throws IOException {

        int ID = 0;
        for(Product product : Inventory.getAllProducts()) {

        if(product.getProductID() > ID)
        ID = product.getProductID();

        }

            
            String name = NameAddPartText.getText();    
            int inventory = Integer.parseInt(InventoryAddPartText.getText());
            double priceCost = Double.parseDouble(AddPriceTextField.getText());
            int max = Integer.parseInt(MaxAddPartText.getText());
            int min = Integer.parseInt(MinAddPartText.getText());
            IDAddPartText.setText(String.valueOf(++ID));     
          
            try{
            
              newProduct.setProductID(ID);
              newProduct.setName(name);
              newProduct.setPrice(priceCost);
              newProduct.setMax(max);
              newProduct.setMin(min);
              newProduct.setStock(inventory);

              Inventory.addProduct(newProduct);  
           
                
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();
            }
            catch(NumberFormatException e){
       
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
            
        }  
    }

    @FXML
    void SaveProductTextField(ActionEvent event) {

    }

    @FXML
    void searchProductHandler(ActionEvent event) {

        String x = SaveProductTextField.getText();
        partsTable.getSelectionModel().select(Part.searchPart(x));
    }
    
    public void updatePartTable() {
        partsTable.setItems(Inventory.getAllParts());
        
    }
}
