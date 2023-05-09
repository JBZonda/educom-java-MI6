package nu.educom.MI6;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Repository rep = new Repository();
    IView view = new JPaneView();
    //IView view = new ConsoleView();
    MI6Model mdl = new MI6Model(rep);
    Presentor presentor = new Presentor(view,mdl);
    presentor.run();
    rep.factory.close();
  }


}