package com.example.supplychainrushi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    public TableView<Product> productTable;

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("  Id  "); //name displayed on table
        id.setCellValueFactory(new PropertyValueFactory<>("id")); //id as specified in product class
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1, "Lenovo", 84449));
//        data.add(new Product(1, "ASUS", 99999));
        ObservableList<Product> products = Product.getAllProducts();
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //to resize the table & avoid extra empty columns
        Pane tabelPane = new Pane();
        //    tabelPane.setStyle("-fx-background-color: #C0C0C0");
        tabelPane.setMaxSize(SupplyChain.width, SupplyChain.height);
        tabelPane.getChildren().addAll(productTable);
        return tabelPane;
    }

    public Pane getProductsByName(String productName){
        TableColumn id = new TableColumn("Id"); //name displayed on table
        id.setCellValueFactory(new PropertyValueFactory<>("id")); //id as specified in product class
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> products = Product.getProductsByName(productName);
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //to resize the table & avoid extra empty columns
        Pane tabelPane = new Pane();
        tabelPane.setMaxSize(SupplyChain.width, SupplyChain.height);
        tabelPane.getChildren().addAll(productTable);
        return tabelPane;
    }

    public Product getSelectedProduct(){
        try{
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}