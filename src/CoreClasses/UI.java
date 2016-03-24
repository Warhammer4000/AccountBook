package CoreClasses;

import UserIntefaceFX.LoginWindow;

import java.util.*;

/**
 * Created by tazim on 3/21/2016.
 */
public class UI {
    private LinkedList<User> Users=new LinkedList<>();


    public boolean register(String name, String password) {
        //check if input is not empty
        if(!name.isEmpty() && !password.isEmpty()){
            //check if already exists
                if(Search(name)==null){
                    User temp = new User(name, password);
                    Users.add(temp);
                    return  true;
                }
                else {
                    LoginWindow.status.setText("User Exists");
                    return  false;
                }

        }
        else {
            LoginWindow.status.setText("Text Field Empty");
            return  false;
        }


    }

    public User login(String name, String password) {
        return Search(name,password);
    }

    public User Search(String name, String password) {
        int length = Users.size();
        User temp=null;

        for (int i = 0; i < length; i++) {
            temp = Users.get(i);
            if (temp.getName().equals(name) && temp.getPassword().equals(password) ) {
                return temp;
            }
        }
        temp=null;
        return temp;

    }

    public User Search(String name) {
        int length = Users.size();
        User temp=null;

        for (int i = 0; i < length; i++) {
            temp = Users.get(i);
            if (temp.getName().equals(name)) {
                return temp;
            }
        }
        temp=null;
        return temp;

    }

}
