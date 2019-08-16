    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ryanh
 */
    public class Product  {
    
        private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        private int productID;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

    public Product(int productID, String name, double price, int stock, int min, int max) {
        
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts (Part part) {
        associatedParts.add(part);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static Product searchProduct(String lookName) {
        
        for (Product p:Inventory.getAllProducts()) {
        if(p.getName().contains(lookName) || new Integer (p.getProductID()).toString().equals(lookName)) return p;
    }
        return null;
    }
    
    }


