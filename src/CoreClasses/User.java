package CoreClasses;

import java.util.*;

/**
 * Created by tazim on 3/21/2016.
 */
public class User {
    private String name;
    private String password;
    private LinkedList<Account> accountBook=new LinkedList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;

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
