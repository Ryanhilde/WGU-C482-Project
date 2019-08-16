package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Inventory;
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

public class ModifyPartController {

    Part selectedPart;
    int selectedIndex;
    
    
    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameInhouseModifyPartText;

    @FXML
    private TextField InventoryInhouseModifyPartText;

    @FXML
    private TextField PriceCostInhouseModifyPartText;

    @FXML
    private TextField MaxInhouseModifyPartText;

    @FXML
    private TextField MinInhouseModifyPartText;

    @FXML
    private TextField MachineIDInhouseModifyPartText;

    @FXML
    private Button CancellButton;

    @FXML
    private Button SaveButton;

    @FXML
    void InhouseHandler(ActionEvent event) {
        MACIDLbl.setText("Machine ID");

    }

    @FXML
    void InventoryInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MachineIDInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MaxInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void MinInhouseModifyPartText(ActionEvent event) {

    }
    
    @FXML
    private Label MACIDLbl;

    @FXML
    void NameInhouseModifyPartText(ActionEvent event) {

    }

    @FXML
    void OutsourcedHandler(ActionEvent event) {

        MACIDLbl.setText("Company Name");
    }

    @FXML
    void PriceCostInhouseModifyPartText(ActionEvent event) {

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
       
       int id = selectedPart.getPartID();
       String name = NameInhouseModifyPartText.getText();
       int inventory = Integer.parseInt(InventoryInhouseModifyPartText.getText());
       double price = Double.parseDouble(PriceCostInhouseModifyPartText.getText());
       int max = Integer.parseInt(MaxInhouseModifyPartText.getText());
       int min = Integer.parseInt(MinInhouseModifyPartText.getText());
       
       if (InhouseRadioButton.isSelected()) {
           
           int machineID = Integer.parseInt(MachineIDInhouseModifyPartText.getText());
           
           InHouse inhousePart = new InHouse(id, name, price, inventory, min, max, machineID);
           Inventory.getAllParts().set(selectedIndex, inhousePart);
       }
       
       if (OutsourcedRadioButton.isSelected()) {
           
           String companyName = MachineIDInhouseModifyPartText.getText();
          
           Outsourced outsourcedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
           Inventory.getAllParts().set(selectedIndex, outsourcedPart);
       }
        
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       Object scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
       stage.setScene(new Scene((Parent) scene));
       stage.show();
       

    }
    
    public void setPart(Part part, int index) {
        selectedPart = part;
        selectedIndex = index;
        
         if (part instanceof InHouse) {

            InHouse newPart = (InHouse) part;
            InhouseRadioButton.setSelected(true);
            MACIDLbl.setText("Machine ID");
            this.NameInhouseModifyPartText.setText(newPart.getName());
            this.InventoryInhouseModifyPartText.setText((Integer.toString(newPart.getInStock())));
            this.PriceCostInhouseModifyPartText.setText((Double.toString(newPart.getPrice())));
            this.MinInhouseModifyPartText.setText((Integer.toString(newPart.getMin())));
            this.MaxInhouseModifyPartText.setText((Integer.toString(newPart.getMax())));
            this.MachineIDInhouseModifyPartText.setText((Integer.toString(newPart.getMachineID())));

        }

        if (part instanceof Outsourced) {

            Outsourced newPart = (Outsourced) part;
            OutsourcedRadioButton.setSelected(true);
            MACIDLbl.setText("Company Name");
            this.NameInhouseModifyPartText.setText(newPart.getName());
            this.InventoryInhouseModifyPartText.setText((Integer.toString(newPart.getInStock())));
            this.PriceCostInhouseModifyPartText.setText((Double.toString(newPart.getPrice())));
            this.MinInhouseModifyPartText.setText((Integer.toString(newPart.getMin())));
            this.MaxInhouseModifyPartText.setText((Integer.toString(newPart.getMax())));
            this.MachineIDInhouseModifyPartText.setText(newPart.getCompanyName());
        }  
    }
}
