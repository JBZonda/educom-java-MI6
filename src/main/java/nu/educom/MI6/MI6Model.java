package nu.educom.MI6;

import com.mysql.cj.log.Log;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MI6Model {
    Repository rep;
    Integer logInAgent;
    List<Integer> loggedInAgents = new ArrayList<>();
    public MI6Model(Repository repository){
        rep = repository;
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
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException ex){
            return false;
        }
    }

    public boolean validPassPhrase(String sN,String pP){

        return rep.authenticateAgent(Integer.parseInt(sN),pP);
    }

    public void saveLogInAttempt(String serviceNumber, boolean success){
        rep.createLogInAttempt(Integer.parseInt(serviceNumber),success);
    }


    public void addLoggedINList(Integer agentNumber){
        loggedInAgents.add(agentNumber);
    }

    public boolean inLoggedInList(){
        return inArraylist(loggedInAgents,logInAgent);
    }
    public static boolean inArraylist(List<Integer> list, Integer item){
        for (Integer integer : list) {
            if (Objects.equals(integer, item)) {
                return true;
            }
        }
        return false;
    }

    public boolean needTimeOut(int sN){
        List <LogInAttempt> logInAttempt = rep.getLastLogInAttempts(sN, true);

        // first login for service number
        if (logInAttempt.size() == 0) {
            return false;
        }
        return !logInAttempt.get(0).success ;
    }

    public int getTimeOut(int sN){
        LocalDateTime timeout;
        List <LogInAttempt> logInAttempts = rep.getLastLogInAttempts(sN);
        int i = 0;
        int numberOfLogIn = logInAttempts.size();
        while (!logInAttempts.get(i).getSuccess()) {
            i++;
            if (numberOfLogIn == i) {
                i--;
                break;
            }
        }
        double timeOutLength = Math.pow(2,i+1);
        timeout = logInAttempts.get(0).getLoginTime().plusMinutes((long) timeOutLength);
        System.out.println(timeOutLength);

        Duration duration = Duration.between(LocalDateTime.now(), timeout);

        return (int) duration.toSeconds();
    }

    public String makeWelcome() {

        Agent agent = rep.getAgent(logInAgent);
        String welcome = String.format("Welcome Agent %03d \n", agent.getServiceNumber());

        if (agent.getLicenceToKill()) {
            welcome += String.format("You have a Licence To Kill until %s\n\n", agent.getLicenceToKillEnd().toString());
        } else {
            welcome += "You do not have a Licence To Kill\n\n";
        }

        List<LogInAttempt> logInAttempts = rep.getLastLogInAttempts(agent.getServiceNumber());
        if (logInAttempts.get(1).getSuccess()) {
            return welcome;
        }
        welcome += "-------past-failed-logins-------\n";
        int numberOfLogIn = logInAttempts.size();
        int i = 1;
        while (!logInAttempts.get(i).getSuccess()) {
            welcome += logInAttempts.get(i).loginTime.toString() + "\n";
            i++;
            if (numberOfLogIn == i) {
                return welcome;
            }
        }
    return welcome;
    }

}
