package CoreClasses;

import UserIntefaceFX.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static UI userInterface = new UI();
    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginWindow.show(userInterface);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
