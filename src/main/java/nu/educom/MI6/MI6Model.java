package nu.educom.MI6;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MI6Model {
    //LogInAttempt logInAttempt = new LogInAttempt(null,null);

    Integer logInAgent;

    List<Integer> blacklistedAgents = new ArrayList<Integer>();
    List<Integer> loggedInAgents = new ArrayList<Integer>();
    public MI6Model(){
    }

    public void setLogInAgent(String aN){
        logInAgent = Integer.parseInt(aN);
    }

    public int getLogInAgent(){ return logInAgent;}

    public boolean validAgentNumber(String input) {

        if (!isInteger(input)) {
            return false;
        }
        if (input.length() > 3){
            return false;
        }
        int number = Integer.parseInt(input);
        return number > 0 && number < 956;

    }

    public boolean isInteger(String input) {
        try {
            int result = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }

    public boolean validPassPhrase(String input){
        return input.compareTo("For ThE Royal QUEEN") == 0;
    }

    public void addBlackList(Integer agentNumber){
        blacklistedAgents.add(agentNumber);
    }

    public void addLogedINList(Integer agentNumber){
        loggedInAgents.add(agentNumber);
    }

    public boolean inBlacklist(){
        return inArraylist(blacklistedAgents,logInAgent);
    }

    public boolean inLoggedInList(){
        return inArraylist(loggedInAgents,logInAgent);
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
