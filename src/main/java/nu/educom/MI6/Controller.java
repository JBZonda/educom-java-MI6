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
            String agentNumberString = md.view.showInput("Enter agent number:");//JOptionPane.showInputDialog(null, "Enter agent number:");

            while (!validateAgentNumber(agentNumberString) && agentNumberString != null) {
                md.view.showErrorMessage("ACCESS DENIED!");
                agentNumberString = md.view.showInput("Enter agent number:");
            }
            if (agentNumberString == null) {
                break;
            }
            int agentNumber = Integer.parseInt(agentNumberString);
            if (inArraylist(md.loggedInAgents,agentNumber)){
                JOptionPane.showMessageDialog(null, "You are logged in", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else if(inArraylist(md.blacklistedAgents,agentNumber)) {
                md.view.showErrorMessage("ACCESS DENIED! You are blacklisted");

            }
            else {
                String SecretSentence = JOptionPane.showInputDialog(md.view.frame, String.format("%03d Secret sentence: ", agentNumber));

                if (SecretSentence.compareTo("Y") == 0) { //For ThE Royal QUEEN
                    JOptionPane.showMessageDialog(null, String.format("Welcome Agent %03d\n", agentNumber), "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    md.loggedInAgents.add(agentNumber);

                } else {
                    md.view.showErrorMessage("ACCESS DENIED! You are blacklisted");
                    md.blacklistedAgents.add(agentNumber);
                }
            }
        }

    }

    public static boolean validateAgentNumber(String input) {

        if (!isInteger(input)) {
            return false;
        }

        return isValidAgentNumber(input);
    }

    public static boolean isValidAgentNumber(String input){
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
