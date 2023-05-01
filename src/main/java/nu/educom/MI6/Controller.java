package nu.educom.MI6;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public Model md;
    public Controller(Model model) {
        md = model;
    }
    public void run(){
        while(true) {
            while (md.setLogInAttempt()) {
                System.out.println(md.logInAttempt.getAgentNumber());
                if (!validateAgentNumber(md.view.logInAttempt.getAgentNumber())){
                    JOptionPane.showMessageDialog(null, "ACCESS DENIED!", "Error", JOptionPane.ERROR_MESSAGE);
                    md.view.ready = false;
                    break;
                }
                Integer agentNumber = Integer.parseInt(md.logInAttempt.getAgentNumber());
                if (inArraylist(md.loggedInAgents, agentNumber)) {
                    JOptionPane.showMessageDialog(null, "You are logged in", "Error", JOptionPane.ERROR_MESSAGE);
                    md.view.ready = false;
                    break;
                } else if (inArraylist(md.blacklistedAgents, agentNumber)) {
                    JOptionPane.showMessageDialog(null, "ACCESS DENIED! You are blacklisted", "Error", JOptionPane.ERROR_MESSAGE);
                    md.view.ready = false;
                    break;
                }
                if (md.view.logInAttempt.getSecretSentence().compareTo("For ThE Royal QUEEN") == 0) { //For ThE Royal QUEEN
                    JOptionPane.showMessageDialog(null, String.format("Welcome Agent %03d\n", agentNumber), "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    md.loggedInAgents.add(agentNumber);

                } else {
                    JOptionPane.showMessageDialog(null, "ACCESS DENIED! You are blacklisted", "Error", JOptionPane.ERROR_MESSAGE);
                    md.blacklistedAgents.add(agentNumber);
                }
                md.view.ready = false;
            }
        }
    }

    public static boolean validateAgentNumber(String input) {

        if (!isInteger(input)) {
            return false;
        }
        if (input.length() > 3){
            return false;
        }
        int number = Integer.parseInt(input);
        return number > 0 && number < 956;

    }

    public static boolean isInteger(String input) {
        try {
            int result = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean inArraylist(List<Integer> list, Integer item){

        for (int i = 0; i < list.size();i++) {
            if (list.get(i) == item) {
                return true;
            }
        }
        return false;
    }

}
