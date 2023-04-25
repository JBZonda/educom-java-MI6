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
    View view = new View();
    Model mdl = new Model(view);
    Controller controller = new Controller(mdl);
    System.out.println(controller.md.blacklistedAgents);
    controller.run();
  }


}