package UserIntefaceFX;


import CoreClasses.UI;
import CoreClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginWindow {
    public static Label status = new Label("");

    public static Stage window = new Stage();
    public static void show(UI userInterface){

            window.setTitle("Login/Register");
            //setupWindow

            status.setTextFill(Color.web("Red"));

            //set a Vbox on left
            VBox loginLayout= new VBox(5);

            Label loginLable = new Label("User Name");
            TextField userNameTextField = new TextField();
            userNameTextField.setMaxSize(200,20);
            userNameTextField.setPromptText("User Name");

            Label passwordLable = new Label("Password");
            PasswordField passwordTextfield= new PasswordField();
            passwordTextfield.setMaxSize(200,20);
            passwordTextfield.setPromptText("Password");


            //add items
            loginLayout.getChildren().add(loginLable);
            loginLayout.getChildren().add(userNameTextField);
            loginLayout.getChildren().add(passwordLable);
            loginLayout.getChildren().add(passwordTextfield);

            //Butons
            Button loginButton = new Button("Log In!");
            loginButton.setOnAction(e-> {
                String name = userNameTextField.getText();
                String password = passwordTextfield.getText();
                LOGIN(name, password,userInterface);
            });

            Button registerButton = new Button("Register");
            registerButton.setOnAction(e->{
                String name=userNameTextField.getText();
                String password=passwordTextfield.getText();
                Register(name,password,userInterface);
            });

            HBox hBox = new HBox();
            hBox.getChildren().addAll(loginButton,registerButton);
            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);

            loginLayout.getChildren().add(hBox);
            loginLayout.getChildren().add(status);
            loginLayout.setAlignment(Pos.CENTER);




            Scene scene= new Scene(loginLayout,350,500);
            window.setScene(scene);
            window.show();
    }

    public static void LOGIN(String name,String password,UI userInterface){
        User temp=userInterface.login(name, password);
         if(temp!=null){
             UserWindow.show(temp);
             window.close();
         }
        else
         {
             status.setText("User Not Found");
         }

    }

    public static void Register(String name,String password,UI userInterface){
        if(userInterface.register(name,password)){
            status.setTextFill(Color.web("Green"));
            status.setText("User Created");
        }
    }
}
