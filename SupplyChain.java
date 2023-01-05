package com.example.supplychainrushi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class SupplyChain extends Application { //refractoring means renaming
    public static final int  width = 800, height = 600, headerBar = 60;
    Pane bodyPane = new Pane();
    Login login = new Login();
    ProductDetails productDetails = new ProductDetails();

    Button globalLoginButton;
    Label customerEmailLabel = null;
    String customerEmail = null;
    //Creating header bar
    private GridPane headerBar(){

        //adding image
        Image image = null;

        try {
            FileInputStream imageStream = new FileInputStream("D:\\DSA\\Project\\Supply_chain_management_system\\images\\cart.png");
            image = new Image(imageStream,65, 65, false, false);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //-----------------------------------------------------------------
        TextField searchText = new TextField();
        Button searchButton = new Button("Search here");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                productDetails.getProductsByName(productName);
                //clear body & put this new pane in the body
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
            }
        });

        //adding
        globalLoginButton = new Button("Log In");  //login button
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                globalLoginButton.setVisible(false); //hiding button
            }
        });

        //---------------------------------------new code---------------------------------------------
        Button signUpButton = new Button("Sign Up"); //sign up button
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
                signUpButton.setVisible(false);
                globalLoginButton.setVisible(true);
            }
        });
        //----------------------------------------new code--------------------------------------------

        customerEmailLabel = new Label("Welcome User");
        //adding search bar & button
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar-20);
        gridPane.setVgap(2); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        //  gridPane.setAlignment(Pos.CENTER); //to set controls to center
        gridPane.setStyle("-fx-background-color: #0eb39d");


        gridPane.add(new ImageView(image), 1,0, 1, 2); //add(Node child,int columnIndex,int rowIndex,int colspan,int rowspan)
        gridPane.add(searchText, 40, 0, 1,2);
        gridPane.add(searchButton, 41, 0,1,2);
        gridPane.add(globalLoginButton, 70, 0 );
        gridPane.add(customerEmailLabel, 70, 1);
        gridPane.add(signUpButton, 71,0); //new code
        return gridPane;
    }

    private GridPane signUpPage(){   //new code function
        Label emailLabel =  new Label("Email");
        Label passwordLabel =  new Label("Password");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label addressLabel = new Label("Address");
        Label mobileLabel = new Label("Mobile");

        Label messageLabel = new Label("Enter Account Details..");
        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField mobileTextField = new TextField();

        Button signUpButton = new Button("Create Account");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String address = addressTextField.getText();
                String mobile = mobileTextField.getText();

                if (emailTextField.getText().isEmpty())
                    messageLabel.setText("Email cannot be empty");

                else if (passwordField.getText().isEmpty())
                    messageLabel.setText("Password Field cannot be empty");

                else if (firstNameTextField.getText().isEmpty())
                    messageLabel.setText("Name Field cannot be empty");

                else if (mobileTextField.getText().isEmpty())
                    messageLabel.setText("Mobile number cannot be empty");

                else {
                    //entering into db
                    DatabaseConnection databaseConnection = new DatabaseConnection();
                    String query = String.format("INSERT INTO customer(email, password, first_name, last_name, address, mobile) VALUES('%s', '%s', '%s', '%s', '%s', '%s')", email,password, firstName, lastName, address, mobile );
                    int rowCount = 0;
                    try{
                        rowCount = databaseConnection.executeUpdateQuery(query);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if(rowCount != 0){
                        messageLabel.setText("Account Created SuccessFully, Login Now");
                    }
                    else
                        messageLabel.setText("Email already exits");
                }
            };
        });

        //adding image
        Image image = null;

        try {
            FileInputStream imageStream = new FileInputStream("D:\\DSA\\Project\\supply_chain_management_system\\images\\signUp.png");
            image = new Image(imageStream,250, 100, false, false);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(5); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        gridPane.setStyle("-fx-background-color: #C0C0C0"); //setting background colour
        //  gridPane.setStyle("-fx-background-image: url:("loginS.png");");
        gridPane.setAlignment(Pos.CENTER); //to set controls to center


        //adding image position
        gridPane.add(new ImageView(image), 0,0, 5, 1);

        //adding email box on panel
        gridPane.add(emailLabel, 0, 1); //x, y
        gridPane.add(emailTextField,1, 1 ); //x, y

        //adding password field on panel
        gridPane.add(passwordLabel, 0, 2); //x, y
        gridPane.add(passwordField, 1, 2); //x, y

        //adding firstName position
        gridPane.add(firstNameLabel,0, 3);
        gridPane.add(firstNameTextField, 1,3);

        //adding lastName position
        gridPane.add(lastNameLabel,0, 4);
        gridPane.add(lastNameTextField, 1,4);

        //adding address position
        gridPane.add(addressLabel,0, 5);
        gridPane.add(addressTextField, 1,5);

        //adding mobile position
        gridPane.add(mobileLabel,0, 6);
        gridPane.add(mobileTextField, 1,6);

        //adding address position
        gridPane.add(signUpButton,0, 7);
        gridPane.add(messageLabel, 1,7);
        return gridPane;
    }

    private GridPane footerBar(){

        Button addToCartButton = new Button("Add to Cart");
        Button buyNowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productDetails.getSelectedProduct();
                if(Order.placeOrder(customerEmail, selectedProduct)==true){
                    messageLabel.setText("Ordered");
                }
                else{
                    messageLabel.setText("Order Failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar + height + 5);
        gridPane.add(addToCartButton, 0, 0);
        gridPane.add(buyNowButton, 1, 0);
        gridPane.add(messageLabel,2,0);
        return gridPane;
    }
    private GridPane loginPage(){
        Label emailLabel =  new Label("Email");
        Label passwordLabel =  new Label("Password");
        Label messageLabel = new Label("Enter Account Details..");
        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                if(login.customerLogin(email, password)==true){  //getting email & password verified from class login function
                    messageLabel.setText("Login Successful");
                    customerEmail = email;
                    globalLoginButton.setDisable(true);
                    customerEmailLabel.setText("Welcome : " + customerEmail);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }
                else
                    messageLabel.setText("Incorrect credentials");
            }
        });
        //adding image
        Image image = null;

        try {
            FileInputStream imageStream = new FileInputStream("D:\\DSA\\Project\\supply_chain_management_system\\images\\loginS.png");
            image = new Image(imageStream,100, 100, false, false);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(5); //setting vertical gap between controls
        gridPane.setHgap(5); //setting horizontal gap between controls
        gridPane.setStyle("-fx-background-color: #C0C0C0"); //setting background colour

        gridPane.setAlignment(Pos.CENTER); //to set controls to center

        //adding image position
        gridPane.add(new ImageView(image), 2,0);

        //adding email box on panel
        gridPane.add(emailLabel, 1, 3); //x, y
        gridPane.add(emailTextField,2, 3 ); //x, y

        //adding password field on panel
        gridPane.add(passwordLabel, 1, 5); //x, y
        gridPane.add(passwordField, 2, 5); //x, y
        //adding button position
        gridPane.add(loginButton,1, 7);
        gridPane.add(messageLabel, 2,7);
        return gridPane;
    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height+2*headerBar);
        //we need to add all child to the root to run

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar); //to make start login page after headerBar

        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(), bodyPane, footerBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}