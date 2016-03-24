package CoreClasses;

import java.util.*;


public class User {
    private String name;
    private String password;
    private double balance=0;
    private double totalCredit=0;
    private double totalDebit=0;

    private LinkedList<Account> accountBook=new LinkedList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public void addTotalCredit(double Credit) {

        this.totalCredit += Credit;

        creditBalance(Credit);
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void addTotalDebit(double Debit) {

        this.totalDebit += Debit;
        debitBalance(Debit);
    }

    public double getBalance() {
        return balance;
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

    public void addAccount(Account item) {
        accountBook.add(item);
    }

    public void clearAccountBook(){accountBook.clear();}

    public  void removeAccount(int index){
        accountBook.remove(index);
    }

    public LinkedList<Account> getAccountBook() {return accountBook;}


}
