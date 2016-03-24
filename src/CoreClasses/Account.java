package CoreClasses;

import java.time.LocalDate;

/**
 * Created by tazim on 3/21/2016.
 */
public class Account {
    private String accountTitle;
    private double value;
    private String comment;
    private LocalDate date;
    private AccountType accountType;

    public Account() {
        accountTitle = "";
        value = 0.00;
        comment = "";
        date = LocalDate.now();
        accountType = accountType.debit;
    }

    public Account(String accountTitle, double value, String comment, LocalDate date, AccountType accountType) {
        this.accountTitle = accountTitle;
        this.value = value;
        this.comment = comment;
        this.date = date;
        this.accountType = accountType;
    }

    //getters
    public String getAccountTitle() {return accountTitle;}

    public double getValue() {return value;}

    public AccountType getAccountType() {return accountType;}

    public void setValue(double value) {this.value = value;}

    public String getComment() {return comment;}

    //setters
    public void setAccountTitle(String accountTitle) {this.accountTitle = accountTitle;}

    public void setComment(String comment) {this.comment = comment;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public void setAccountType(AccountType accountType) {this.accountType = accountType;}

}
