package CoreClasses;

import UserIntefaceFX.LoginWindow;

import java.io.Serializable;
import java.util.LinkedList;


public class UI implements Serializable {
    private static UI temp = null;
    private LinkedList<User> Users=new LinkedList<>();

    public static UI getInstance() {

        if (temp == null) {
            return temp = new UI();
        } else
            return temp;
    }

    public boolean register(String name, String password) {
        if (validity(name, password)) {
            User temp = new User(name, password);
            Users.add(temp);
            return true;
        } else
            return false;


    }

    public User login(String name, String password) {
        return Search(name,password);
    }

    public User Search(String name, String password) {
        int length = Users.size();
        User temp;

        for (CoreClasses.User User : Users) {
            temp = User;
            if (temp.getName().equals(name) && temp.getPassword().equals(password)) {
                return temp;
            }
        }
        temp=null;
        return temp;

    }

    public User Search(String name) {
        int length = Users.size();
        User temp;

        for (CoreClasses.User User : Users) {
            temp = User;
            if (temp.getName().equals(name)) {
                return temp;
            }
        }
        temp=null;
        return temp;

    }

    public boolean validity(String name, String password) {
        //check if input is not empty
        if (!name.isEmpty() && !password.isEmpty()) {
            //check if already exists
            if (Search(name) == null) {
                return true;
            } else {
                LoginWindow.status.setText("User Exists");
                return false;
            }

        } else {
            LoginWindow.status.setText("Text Field Empty");
            return false;
        }
    }

}
