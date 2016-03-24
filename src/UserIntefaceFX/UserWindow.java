package UserIntefaceFX;

import CoreClasses.Account;
import CoreClasses.AccountType;
import CoreClasses.User;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * Created by tazim on 3/23/2016.
 */
public class UserWindow {

    public static Stage window=new Stage();
    public static TableView<Account> table;
    public static Label statusLable=new Label("");

    public static void show(User user){
        window.setTitle("User:"+user.getName());

        BorderPane mainLayout= new BorderPane();


        //topLayout
        VBox topLayout=new VBox(20);
        MenuBar menuBar=CustomMenuBar.createMenubar(user);
        topLayout.getChildren().add(menuBar);

        //rightLayout
        VBox rightLayout=new VBox(20);
        Button addButton=new Button("+Add");
        addButton.setPrefSize(70,60);
        addButton.setOnAction(event -> Add());

        //CenterLayout
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //columns
        //title
        TableColumn<Account,String> titleColumn=new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("accountTitle"));

        //Value
        TableColumn<Account,Double> valueColumn=new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //comment
        TableColumn<Account,String> commentColumn=new TableColumn<>("Comment");
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        //Date
        TableColumn<Account,LocalDate> dateColumn=new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        //AccountType
        TableColumn<Account,AccountType> accountTypeColumn=new TableColumn<>("AccountType");
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        //addColumns on table
        table.getColumns().addAll(titleColumn,valueColumn,dateColumn,accountTypeColumn,commentColumn);


        Button deleteButton=new Button("Delete");
        deleteButton.setPrefSize(70,60);
        deleteButton.setOnAction(event -> Delete());

        rightLayout.setAlignment(Pos.CENTER);
        rightLayout.getChildren().addAll(addButton,deleteButton);


        //SetBorderPane
        mainLayout.setTop(topLayout);
        mainLayout.setRight(rightLayout);
        mainLayout.setCenter(table);
        mainLayout.setBottom(statusLable);

        mainLayout.setPrefSize(800,600);
        Scene scene=new Scene(mainLayout);
        //window.setFullScreen(true);
        window.setScene(scene);
        window.show();
    }

    public static void Add(){
        Stage addNewAccount= new Stage();
        addNewAccount.setTitle("Add New Account");
        addNewAccount.initModality(Modality.APPLICATION_MODAL);
        VBox vBox=new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        //lables
        Label titleLabel=new Label("Account Title");
        Label valueLable=new Label("Value");
        Label commentLable=new Label("Comment");
        Label dateLable=new Label("Date");
        Label accountTypeLable=new Label("Account Type");
        Label statusLable= new Label("");

        //ComboBox
        ComboBox<String> title=new ComboBox<>();
        title.getItems().addAll(
                "Food",
                "Transport",
                "Allowance"
        );

        title.setEditable(true);

        TextField valueTextField= new TextField();
        valueTextField.setPromptText("Ex.500");
        valueTextField.setMaxSize(200,20);

        TextArea commentTextArea=new TextArea();
        commentTextArea.setMaxSize(300,300);

        DatePicker datePicker= new DatePicker();
        datePicker.setValue(LocalDate.now());

        ComboBox<AccountType> accountType=new ComboBox<>();
        accountType.setValue(AccountType.credit);
        accountType.getItems().addAll(AccountType.credit,AccountType.debit);

        //button
        Button submitButton= new Button("Submit");
        submitButton.setPrefSize(80,50);
                                                //Didn't make function coz too many parameters
        submitButton.setOnAction(event -> {
            boolean allOk=false;
            int count=0;
            Account account=new Account();

            //title Check
            if(title.getValue()!=null){
                account.setAccountTitle(title.getValue());
                count++;
            }
            else{
                statusLable.setText("Please Enter A valid Title & press Enter");
                statusLable.setTextFill(Color.web("Red"));
                count--;
            }

            //Value Check
            try {
                double d = Double.parseDouble(valueTextField.getText());
                account.setValue(d);
                count++;
            }
            catch (NumberFormatException ex) {
                statusLable.setText("Please Enter A valid Value in Numbers");
                statusLable.setTextFill(Color.web("Red"));
                count--;
            }


            account.setComment(commentTextArea.getText());
            account.setDate(datePicker.getValue());
            account.setAccountType(accountType.getValue());

            if(count>=2){
                allOk=true;
            }

            if(allOk){
                statusLable.setText("Account Successfully Added");
                statusLable.setTextFill(Color.web("Green"));
                table.getItems().addAll(account);
            }

        });




        //add all items into vbox
        vBox.getChildren().addAll(titleLabel,title);
        vBox.getChildren().addAll(valueLable,valueTextField);
        vBox.getChildren().addAll(commentLable,commentTextArea);
        vBox.getChildren().addAll(dateLable,datePicker);
        vBox.getChildren().addAll(accountTypeLable,accountType);
        vBox.getChildren().addAll(submitButton,statusLable);

        //set Scene and show
        Scene scene = new Scene(vBox,400,450);
        addNewAccount.setScene(scene);
        addNewAccount.show();

    }

    public static void Delete(){
        ObservableList<Account> accountSelected , allAccounts;
        allAccounts=table.getItems();
        accountSelected=table.getSelectionModel().getSelectedItems();
        accountSelected.forEach(allAccounts::remove);
        statusLable.setText("Row Deleted");
        statusLable.setTextFill(Color.web("Green"));
    }



}
