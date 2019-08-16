package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MainScreenController implements Initializable {
  
    @FXML
    private Button SearchPart;

    @FXML
    private TextField SearchPartText;

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
    private Button AddPart;

    @FXML
    private Button ModifyPart;

    @FXML
    private Button DeletePart;

    @FXML
    private Button SearchProduct;

    @FXML
    private TextField SearchProductText;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;

    @FXML
    private TableColumn<Product, Double> PricePerUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private Button ModifyProdcut;

    @FXML
    private Button DeleteProduct;

    @FXML
    private Button Exit;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        productTable.setItems(Inventory.getAllProducts());
              
        ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
     
        partsTable.setItems(Inventory.getAllParts());
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
       
    }

    @FXML
    void SearchTextPartHandler(ActionEvent event) {

        
    }

    @FXML
    void SearchTextProductHandler(ActionEvent event) {
        //use linear search
            

    }

    @FXML
    void addPartHandler(ActionEvent event) throws IOException {


        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        

    }

    @FXML
    void addProductHandler(ActionEvent event) throws IOException {
        
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    @FXML
    void deletePartHandler(ActionEvent event) {
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            ObservableList<Part> allParts, singlePart;
            allParts = partsTable.getItems();
            singlePart = partsTable.getSelectionModel().getSelectedItems();
            singlePart.forEach(allParts::remove); 

        }
    }

    @FXML
    void deleteProductHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Product, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            ObservableList<Product> allProdcuts, singlePart;
            allProdcuts = productTable.getItems();
            singlePart = productTable.getSelectionModel().getSelectedItems();
            singlePart.forEach(allProdcuts::remove); 

        }
    }

    @FXML
    void exitHandler(ActionEvent event) {
        
        System.exit(0);

    }

    @FXML
    void modifyPartHandler(ActionEvent event) throws IOException {

        
        Stage stage; 
        Parent root;       
        stage=(Stage) ModifyPart.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "/View_Controller/ModifyPart.fxml"));
        
        root =loader.load();
        ModifyPartController controller = loader.getController();
        Part part=partsTable.getSelectionModel().getSelectedItem();
        int index = partsTable.getSelectionModel().getSelectedIndex();
        
        if(part != null) {
            controller.setPart(part, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
       
        
        
    }

    @FXML
    void modifyProductHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) ModifyProdcut.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "/View_Controller/ModifyProduct.fxml"));
        
        root =loader.load();
        ModifyProductController controller = loader.getController();
        Product product=productTable.getSelectionModel().getSelectedItem();
        int index = productTable.getSelectionModel().getSelectedIndex();
        
        if(product != null) {
            controller.setProduct(product, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void searchPartHandler(ActionEvent event) {

        String x = SearchPartText.getText();
        partsTable.getSelectionModel().select(Part.searchPart(x)); 

    }

    @FXML
    void searchProductHandler(ActionEvent event) {
        
        String x2 = SearchProductText.getText();
        productTable.getSelectionModel().select(Product.searchProduct(x2));

    }
}
