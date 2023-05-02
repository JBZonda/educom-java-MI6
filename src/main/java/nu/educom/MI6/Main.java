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
    IView view = new ConsoleView();
    MI6Model mdl = new MI6Model();
    Presentor presentor = new Presentor(view,mdl);
  }


}