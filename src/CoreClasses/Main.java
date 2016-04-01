package CoreClasses;

import UserIntefaceFX.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;


public class Main extends Application {

    public static UI userInterface = UI.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    public static void save() {
        String username = System.getProperty("user.name");

        File file = new File("C:\\Users\\" + username + "\\AppData\\Local\\AccountBook\\UserData.ser");

        try (FileOutputStream fileOut = new FileOutputStream(file)) {

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userInterface);
            System.out.println("File saved");
            out.close();
            fileOut.close();


        } catch (IOException i) {
            System.out.println("IO Exception");
        }
    }

    public static void load() {
        String username = System.getProperty("user.name");
        File file = new File("C:\\Users\\" + username + "\\AppData\\Local\\AccountBook\\UserData.ser");
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userInterface = (UI) in.readObject();
            System.out.println("File Successfully loded");
            in.close();
            fileIn.close();

        } catch (IOException i) {
            System.out.println("IO Exception");
        } catch (ClassNotFoundException c) {
            System.out.println("UI class not found");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        load();
        save();
        LoginWindow.show(userInterface);



    }
}
