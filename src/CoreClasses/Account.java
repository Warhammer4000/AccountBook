package CoreClasses;

import java.io.Serializable;
import java.time.LocalDate;


public class Account implements Serializable {
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
        accountType = AccountType.debit;
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

    //setters
    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {this.value = value;}

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {this.comment = comment;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

}
