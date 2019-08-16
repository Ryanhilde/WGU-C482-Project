package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartController {

    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField PriceCostAddPartText;

    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField MaxAddPartText;

    @FXML
    private TextField MinAddPartText;

    @FXML
    private TextField MachineIDAddPartText;
    
    @FXML
    private TextField CompanyNameAddPartText;

    @FXML
    private Button CancellButton;

    @FXML
    private Button SaveButton;

    private boolean isOutsourced = true;
        
    @FXML
    private Label MACIDLbl;

    
    @FXML
    void IDAddPartText(ActionEvent event) {

       
    }

    @FXML
    void InhouseHandler(ActionEvent event) {

        isOutsourced = false;
        MACIDLbl.setText("Machine ID");
        
    }

    @FXML
    void InventoryAddPartText(ActionEvent event) {

    }

    @FXML
    void MachineIDAddPartText(ActionEvent event) {

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
    void OutsourcedHandler(ActionEvent event) {
        
        isOutsourced = true;
        MACIDLbl.setText("Company Name");
    }

    @FXML
    void PriceCostAddPartText(ActionEvent event) {

    }

    @FXML
    void cancelHandler(ActionEvent event) throws IOException {
        
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
    void saveHandler(ActionEvent event) throws IOException {

        int ID = 0;
        for(Part part : Inventory.getAllParts()) {

            if(part.getPartID() > ID)

                ID = part.getPartID();

        }

            IDAddPartText.setText(String.valueOf(++ID));     
            String name = NameAddPartText.getText();    
            int inventory = Integer.parseInt(InventoryAddPartText.getText());
            double priceCost = Double.parseDouble(PriceCostAddPartText.getText());
            int max = Integer.parseInt(MaxAddPartText.getText());
            int min = Integer.parseInt(MinAddPartText.getText());
            

          
            try{
            
                if(min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value.");
                    alert.showAndWait();
            }
                else if (inventory > max || inventory < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
                    alert.showAndWait();
                }
                
                else {
                
                if (InhouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(MachineIDAddPartText.getText());
                    InHouse addPart = new InHouse(ID, name, priceCost, inventory, min, max, machineID);
                
                    Inventory.addPart(addPart); 
            }
                if (OutsourcedRadioButton.isSelected()) {
                    String companyName = MachineIDAddPartText.getText();
                    Outsourced addPart = new Outsourced(ID, name, priceCost, inventory, min, max, companyName);
                
                    Inventory.addPart(addPart);  
            } 
           
                
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
                }
 
        }
        
        catch(NumberFormatException e){
       
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
            
        }    
    }
}


