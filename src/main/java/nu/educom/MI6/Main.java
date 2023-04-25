package nu.educom.MI6;
import java.sql.Array;
import java.sql.Struct;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
public class Main {
  public static void main(String[] args) {
    // --------------------------
    JFrame frame = new JFrame("Agent log in");
    //String name = JOptionPane.showInputDialog(frame, "What's your name?");
    //JOptionPane.showMessageDialog(null, "ACCESS DENIED!", "Error", JOptionPane.ERROR_MESSAGE);
    //System.out.printf("The user's name is '%s'.\n", name);
    //System.exit(0);

    // --------------------------

    List<Integer> blacklistedAgents = new ArrayList<Integer>();
    List<Integer> loggedInAgents = new ArrayList<Integer>();
    Scanner myObj = new Scanner(System.in);

    while(true) {
      String agentNumberString = JOptionPane.showInputDialog(frame, "Enter agent number:");

      while (!validateAgentNumber(agentNumberString) && agentNumberString != null) {
        JOptionPane.showMessageDialog(null, "ACCESS DENIED!", "Error", JOptionPane.ERROR_MESSAGE);
        agentNumberString = JOptionPane.showInputDialog(frame, "Enter agent number:");
      }
      if (agentNumberString == null) {
        break;
      }
      int agentNumber = Integer.parseInt(agentNumberString);
      if (inArraylist(loggedInAgents,agentNumber)){
        System.out.println("You are logged in\n\n");
        JOptionPane.showMessageDialog(null, "You are logged in", "Error", JOptionPane.ERROR_MESSAGE);

      }
      else if(inArraylist(blacklistedAgents,agentNumber)) {
        System.out.println("You are blacklisted\n\n");
        JOptionPane.showMessageDialog(null, "ACCESS DENIED! You are blacklisted", "Error", JOptionPane.ERROR_MESSAGE);

      }
      else {
        String SecretSentence = JOptionPane.showInputDialog(frame, String.format("%03d Secret sentence: ", agentNumber));

        if (SecretSentence.compareTo("Y") == 0) { //For ThE Royal QUEEN
          System.out.println(String.format("Welcome Agent %03d\n", agentNumber));
          JOptionPane.showMessageDialog(null, String.format("Welcome Agent %03d\n", agentNumber), "Welcome", JOptionPane.INFORMATION_MESSAGE);
          loggedInAgents.add(agentNumber);

        } else {
          JOptionPane.showMessageDialog(null, "ACCESS DENIED!", "Error", JOptionPane.ERROR_MESSAGE);
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

  public static boolean inArraylist(List<Integer> list, Integer item){

    for (int i = 0; i < list.size();i++) {
      if (list.get(i) == item) {
        return true;
      }
    }
    return false;
  }


}