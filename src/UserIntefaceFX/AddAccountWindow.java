package UserIntefaceFX;

import CoreClasses.Account;
import CoreClasses.AccountType;
import CoreClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;


public class AddAccountWindow {
    public static TextField valueTextField;
    protected static Label statusLable;
    protected static TextArea commentTextArea;
    protected static DatePicker datePicker;

    private static Stage window = new Stage();
    public static void setUpStage(User user) {

        initialize();

        ComboBox<String> title = new ComboBox<>();
        ComboBox<AccountType> accountType = new ComboBox<>();


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Account");
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        //lables
        Label titleLabel = new Label("Account Title");
        Label valueLable = new Label("Value");
        Label commentLable = new Label("Comment");
        Label dateLable = new Label("Date");
        Label accountTypeLable = new Label("Account Type");


        //ComboBox

        title.getItems().addAll(
                "Food",
                "Transport",
                "Allowance"
        );

        title.setEditable(true);

        accountType.setValue(AccountType.credit);
        accountType.getItems().addAll(AccountType.credit, AccountType.debit);


        //button
        Button submitButton = new Button("Submit");
        submitButton.setPrefSize(80, 50);

        //Didn't make function coz too many parameters
        submitButton.setOnAction(event -> buttonPressed(user, title, accountType));


        //add all items into vbox
        vBox.getChildren().addAll(titleLabel, title);
        vBox.getChildren().addAll(valueLable, valueTextField);
        vBox.getChildren().addAll(commentLable, commentTextArea);
        vBox.getChildren().addAll(dateLable, datePicker);
        vBox.getChildren().addAll(accountTypeLable, accountType);
        vBox.getChildren().addAll(submitButton, statusLable);


        //set Scene and show
        Scene scene = new Scene(vBox, 400, 450);
        window.setScene(scene);
        window.show();
    }

    public static void show(User user) {
        setUpStage(user);
    }

    public static void buttonPressed(User user, ComboBox<String> title, ComboBox<AccountType> accountType) {
        UserWindow.statusLable.setText("");//ignore this line

        boolean allOk = false;
        int count = 0;
        Account account = new Account();

        //title Check
        if (title.getValue() != null) {
            account.setAccountTitle(title.getValue());
            count++;
        } else {
            statusLable.setText("Please Enter A valid Title & press Enter");
            statusLable.setTextFill(Color.web("Red"));
            count--;
        }

        //Value Check
        try {
            double d = Double.parseDouble(valueTextField.getText());
            account.setValue(d);
            count++;
        } catch (NumberFormatException ex) {
            statusLable.setText("Please Enter A valid Value in Numbers");
            statusLable.setTextFill(Color.web("Red"));
            count--;
        }


        account.setComment(commentTextArea.getText());
        account.setDate(datePicker.getValue());

        account.setAccountType(accountType.getValue());

        if (count >= 2) {
            allOk = true;
        }

        if (allOk) {
            statusLable.setText("Account Successfully Added");
            statusLable.setTextFill(Color.web("Green"));
            UserWindow.table.getItems().addAll(account);

            UserWindow.statusLable.setTextFill(Color.web("green"));
            UserWindow.statusLable.setText("New Row Added");
            UserWindow.updateAccountStatus(user);
            reset(title);

        }
    }

    public static void initialize() {
        valueTextField = new TextField();
        valueTextField.setPromptText("Ex.500");
        valueTextField.setMaxSize(200, 20);


        statusLable = new Label("");


        commentTextArea = new TextArea();
        commentTextArea.setMaxSize(300, 300);

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
    }

    public static void reset(ComboBox<String> title) {
        valueTextField.setText("");
        commentTextArea.setText("");
        title.setValue("");
    }
}
