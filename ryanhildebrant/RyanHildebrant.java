/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryanhildebrant;

import Model.InHouse;
import Model.Inventory;
import Model.Product;
import Model.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ryanh
 */
public class RyanHildebrant extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        
        Part part1 = new InHouse(1, "Part 1", 5.0, 5, 5, 5, 1);
        Inventory.addPart(part1);
        
        Part part2 = new InHouse(2, "Part 2", 10.0, 10, 10, 10, 10);
        Inventory.addPart(part2);
        
        Part part3 = new InHouse(3, "Part 3", 15.0, 12, 12, 12, 12);
        Inventory.addPart(part3);
       
        Product product1 = new Product(1, "Product 1", 5.0, 5, 5, 5);
        Inventory.addProduct(product1);
        
        Product product2 = new Product(2, "Product 2", 10.0, 10, 10, 10);
        Inventory.addProduct(product2);
        
        Product product3 = new Product(3, "Product 3", 15.0, 12, 12, 12);
        Inventory.addProduct(product3);
    
        
        launch(args);
    }
    
}
