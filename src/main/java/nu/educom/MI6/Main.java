package nu.educom.MI6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class Main {
  public static void main(String[] args) {
    Repository rep = new Repository();
    System.out.println(rep.authenticateAgent(7,"For ThE Royal QUEEN"));
    /*
    //IView view = new ConsoleView();
    IView view = new JPaneView();
    MI6Model mdl = new MI6Model();
    Presentor presentor = new Presentor(view,mdl);
    presentor.run();*/
  }


}