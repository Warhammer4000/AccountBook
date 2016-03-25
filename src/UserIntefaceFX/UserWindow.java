package UserIntefaceFX;

import CoreClasses.Account;
import CoreClasses.AccountType;
import CoreClasses.User;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class UserWindow {

    public static Stage window=new Stage();
    public static TableView<Account> table;
    public static Label statusLable=new Label("");
    public static Label balanceStatus=new Label("");
    public static Label totalCreditStatus=new Label("");
    public static Label totalDebitStatus=new Label("");




    public static void show(User user){
        window.setTitle("User:"+user.getName());

        //CenterLayout
        table = user.getTable();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);

        BorderPane mainLayout= new BorderPane();


        //topLayout
        VBox topLayout=new VBox(20);
        MenuBar menuBar=CustomMenuBar.createMenubar(user);


        //statusBar
        HBox accountStatus= new HBox(20);
        Label balanceLable= new Label("Current Balance:");

        balanceStatus= new Label(String.valueOf(user.getBalance()));
        balanceStatus.setTextFill(Color.web("Green"));

        Label totalCreditLable= new Label("Total Credit:");
        Label totalDebitLable= new Label("Total Debit:");

        totalCreditStatus.setText(String.valueOf(user.getTotalCredit()));
        totalDebitStatus.setText(String.valueOf(user.getTotalDebit()));

        totalCreditStatus.setTextFill(Color.web("Red"));
        totalDebitStatus.setTextFill(Color.web("Green"));

        accountStatus.getChildren().addAll(balanceLable,balanceStatus,totalCreditLable,totalCreditStatus,totalDebitLable,totalDebitStatus);

        topLayout.getChildren().addAll(menuBar,accountStatus);

        //rightLayout
        VBox rightLayout=new VBox(20);

        //Buttons
        Button addButton=new Button("+Add");
        addButton.setPrefSize(70,60);
        addButton.setOnAction(event -> Add(user));

        Button editButton = new Button("Edit");
        editButton.setPrefSize(70, 60);
        editButton.setOnAction(event -> Edit(user, table));





        Button deleteButton=new Button("Delete");
        deleteButton.setPrefSize(70,60);
        deleteButton.setOnAction(event -> Delete(user));

        rightLayout.setAlignment(Pos.CENTER);
        rightLayout.getChildren().addAll(addButton, editButton, deleteButton);


        //SetBorderPane
        mainLayout.setTop(topLayout);
        mainLayout.setRight(rightLayout);
        mainLayout.setCenter(table);
        mainLayout.setBottom(statusLable);

        mainLayout.setPrefSize(800,600);
        Scene scene=new Scene(mainLayout);
        window.setScene(scene);
        window.show();
    }

    public static void Add(User user){
        AddAccountWindow.show(user);
    }

    public static void Edit(User user, TableView<Account> table) {

        if (!table.getSelectionModel().isEmpty()) {
            EditAccountWindow.show(user, table);
            updateAccountStatus(user);
        } else {
            statusLable.setText("No Rows Selected");
            statusLable.setTextFill(Color.web("Blue"));
        }


    }

    public static void Delete(User user){
        ObservableList<Account> accountSelected , allAccounts;
        accountSelected=table.getSelectionModel().getSelectedItems();
        allAccounts=table.getItems();

        if(!table.getSelectionModel().isEmpty()){
            accountSelected.forEach(allAccounts::remove);
            statusLable.setText("Row Deleted");
            statusLable.setTextFill(Color.web("Red"));
            updateAccountStatus(user);
        }
        else {
            statusLable.setText("No Rows Selected");
            statusLable.setTextFill(Color.web("Blue"));
        }


    }

    public static void updateAccountStatus(User user){
        ObservableList<Account> allAcounts;
        allAcounts=table.getItems();
        //reset all values before calculating each time.
        //it sums up all data each time
        user.setBalance(0);
        user.setTotalCredit(0);
        user.setTotalDebit(0);


        for (Account allAcount : allAcounts) {
            //get value and account type then do stuff
            double value = allAcount.getValue();
            AccountType accountType = allAcount.getAccountType();

            //Update credit/Debit and update Balance
            if (accountType == AccountType.credit) {
                user.addTotalCredit(value);
            }

            if (accountType == AccountType.debit) {
                user.addTotalDebit(value);
            }

        }


        //set color depnding on balance

        if(user.getBalance()<0)
        {
            balanceStatus.setTextFill(Color.web("red"));
        }
        else
        {
            balanceStatus.setTextFill(Color.web("green"));
        }

        balanceStatus.setText(String.valueOf(user.getBalance()));
        totalCreditStatus.setText(String.valueOf(user.getTotalCredit()));
        totalDebitStatus.setText(String.valueOf(user.getTotalDebit()));

    }

}
