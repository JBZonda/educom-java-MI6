package nu.educom.MI6;
import java.sql.Array;
import java.sql.Struct;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> blacklistedAgents = new ArrayList<Integer>();
    ArrayList<Integer> loggedInAgents = new ArrayList<Integer>();
    System.out.println(inArraylist(loggedInAgents,1));
    Scanner myObj = new Scanner(System.in);

    while(true) {
      System.out.println("Enter agent number:");
      String agentNumberString = myObj.nextLine();

      while (!validateAgentNumber(agentNumberString) && agentNumberString.compareTo("quit") != 0) {
        System.out.println("Invalid agent number:");
        System.out.println("Enter agent number:");
        agentNumberString = myObj.nextLine();
      }
      if (agentNumberString.compareTo("quit") == 0) {
        break;
      }
      int agentNumber = Integer.parseInt(agentNumberString);
      if (inArraylist(loggedInAgents,agentNumber)){
        System.out.println("You are logged in\n\n");
      }
      else if(inArraylist(blacklistedAgents,agentNumber)) {
        System.out.println("You are blacklisted\n\n");
      }
      else {
        System.out.println(String.format("%03d Secret sentence: ", agentNumber));
        String SecretSentence = myObj.nextLine();
        if (SecretSentence.compareTo("For ThE Royal QUEEN") == 0) {
          System.out.println(String.format("Welcome Agent %03d\n", agentNumber));
          loggedInAgents.add(agentNumber);

        } else {
          System.out.println("Wrong Sentence");
          blacklistedAgents.add(agentNumber);
        }
      }
    }

  }

  public static boolean validateAgentNumber(String input) {

    if (!isInteger(input)) {
      return false;
    }

    if (!isValidAgentNumber(input)) {
      return false;
    }

    return true;
  }

  public static boolean isValidAgentNumber(String input){
    if (input.length() > 3){
      return false;
    }
    int number = Integer.parseInt(input);
    if (number > 0 && number < 956){
      return true;
    }
    return false;

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

  public static boolean inArraylist(ArrayList<Integer> list, Integer item){

    for (int i = 0; i < list.size();i++) {
      if (list.get(i) == item) {
        return true;
      }
    }
    return false;
  }

}