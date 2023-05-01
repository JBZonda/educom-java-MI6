package nu.educom.MI6;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    View view;
    LogInAttempt logInAttempt;
    List<Integer> blacklistedAgents = new ArrayList<Integer>();
    List<Integer> loggedInAgents = new ArrayList<Integer>();
    public Model(View view){
        this.view = view;
    }

    public boolean setLogInAttempt(){
        System.out.print("");

        if (getLogInReady()){
            this.logInAttempt = view.getLogIn();
            return true;
        }else {
            return false;
        }

    }

    public boolean getLogInReady(){
        return view.ready;
    }

}
