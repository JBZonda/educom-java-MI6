package nu.educom.MI6;
import java.util.Scanner;
public class ConsoleView implements IView{
    IPresentor pres;
    Scanner scan = new Scanner(System.in);
    String agentNumberString;
    String passPhrase;
    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void triggerAskLogin() {
        System.out.println("Enter agent number:");
        agentNumberString = scan.nextLine();
        pres.handleLogin();
    }

    @Override
    public String getServiceNumber() { return agentNumberString;}

    @Override
    public void triggerAskPassword() {
        System.out.println(String.format("Enter password phrase"));
        passPhrase = scan.nextLine();
        pres.handlePassword();
    }

    @Override
    public String getPassPhrase() {
        return passPhrase;
    }

    @Override
    public void close() {
    }

    @Override
    public void addPresentorListener(IPresentor p) {
        pres = p;

    }
}
