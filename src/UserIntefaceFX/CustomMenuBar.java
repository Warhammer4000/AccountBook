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
        //-------------------------------------------------------------------------------------------------------------

        //TableMenu
        Menu tableMenu = new Menu();
        tableMenu.setText("Table Menu");


        /// unfinished
        MenuItem clearTable = new MenuItem("Clear Table");
        clearTable.setOnAction(e -> {
            //resetTable(user);

        });

        tableMenu.getItems().add(clearTable);
        //other menu

        //------------------------------------------------------------------------------------------------------------------
        menuBar.getMenus().addAll(accountMenu, tableMenu);



        return menuBar;
    }


    //User Menu
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


    //table Menu
    public static void resetTable(User user) {


        for (Object o : user.getTable().getItems()) {
            user.getTable().getItems().remove(o);
        }


        UserWindow.statusLable.setText("Table Cleared");
        UserWindow.statusLable.setTextFill(Color.web("Red"));
        UserWindow.updateAccountStatus(user);
    }




}
