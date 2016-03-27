package UserIntefaceFX;

import CoreClasses.Main;
import CoreClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomMenuBar {
    public static MenuBar createMenubar(User user){
        MenuBar menuBar=new MenuBar();

        //AccountMenu
        Menu accountMenu= new Menu();
        accountMenu.setText("Account");

        MenuItem changePassword= new MenuItem("Change Password");
        changePassword.setOnAction(event -> passwordChange(user));
        accountMenu.getItems().add(changePassword);

        MenuItem logout= new MenuItem("Logout");
        logout.setOnAction(event -> userLogout(user));
        accountMenu.getItems().add(logout);


        menuBar.getMenus().addAll(accountMenu);



        return menuBar;
    }

    public static void passwordChange(User user){
        Stage passowordChangeWindow= new Stage();
        passowordChangeWindow.setTitle("Change Password");
        Label status=new Label("");


        VBox vBox=new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        PasswordField oldPass= new PasswordField();
        oldPass.setMaxSize(200,20);

        PasswordField newPass= new PasswordField();
        newPass.setMaxSize(200,20);

        Label label1= new Label("Old Password");
        Label label2=new Label("New Password");

        Button submitButton= new Button("Submit");
        submitButton.setOnAction(event -> {
            if(user.getPassword().equals(oldPass.getText())){
                user.setPassword(newPass.getText());
                status.setTextFill(Color.web("Green"));
                status.setText("Password Change Successful");
            }
            else{
                status.setTextFill(Color.web("Red"));
                status.setText("Old Password Doesn't Match");
            }


        });
        vBox.getChildren().addAll(label1,oldPass,label2,newPass,submitButton,status);

        Scene scene= new Scene(vBox,300,400);
        passowordChangeWindow.setScene(scene);
        passowordChangeWindow.show();

    }
    public static void userLogout(User user){
        UserWindow.window.close();
        LoginWindow.show(Main.userInterface);
    }


}
