package nu.educom.MI6;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Repository rep = new Repository();

    //IView view = new ConsoleView();
    IView view = new JPaneView();
    MI6Model mdl = new MI6Model(rep);
    Presentor presentor = new Presentor(view,mdl);
    presentor.run();
  }


}