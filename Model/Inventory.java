/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ryanh
 */
public class Inventory {
    
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    
    public int partListSize() {
	return allParts.size();
	}
    
    public static void addPart(Part part) {
        allParts.add(part);
    }
    
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    
    public static Part lookupPart(int partId) {
        
        return null;
    }
    
    public static Product lookupProduct(int productId) {
        return null; //not sure what to return here
    }
    
    public static ObservableList<Part> lookupPart(String partName) {
        return null; //not sure what to return here
    }
    
        public static ObservableList<Product> lookupProduct(String productName) {
        return null; //not sure what to return here
    }
        
    public static void updatePart(int index, Part newPart) {
        
    }
    
    public static void updateProduct(Product selectedProduct, ArrayList<String> productTextFieldValues[]) {
        
    }

    public static boolean deletePart(Part associatedPart) {

	return true;
    }
    
    public static boolean deleteProduct(int productID) {
        return false; //not sure what to put here
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    

    
}

