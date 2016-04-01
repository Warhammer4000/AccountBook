package CoreClasses;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.time.LocalDate;


public class User implements Serializable {
    private TableView<Account> table;
    private String name;
    private String password;
    private double balance=0;
    private double totalCredit=0;
    private double totalDebit=0;




    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.table=new TableView<>();
        setTable();


    }

    public TableView<Account> getTable() {
        return table;
    }

    public void setTable() {
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
        table.getColumns().add(titleColumn);
        table.getColumns().add(valueColumn);
        table.getColumns().add(dateColumn);
        table.getColumns().add(accountTypeColumn);
        table.getColumns().add(commentColumn);


    }

    public void clearTable() {
        this.table = new TableView<>();
        setTable();
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public void addTotalCredit(double Credit) {

        this.totalCredit += Credit;

        creditBalance(Credit);
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public void addTotalDebit(double Debit) {

        this.totalDebit += Debit;
        debitBalance(Debit);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void debitBalance(double debit) {
        this.balance += debit;
    }

    public void creditBalance(double credit) {
        this.balance -= credit;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
