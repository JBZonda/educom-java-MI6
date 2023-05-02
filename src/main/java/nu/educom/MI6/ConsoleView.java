package nu.educom.MI6;

public class ConsoleView implements IView{
    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void triggerAskLogin() {

    }

    @Override
    public String getServiceNumber() {

        return null;
    }

    @Override
    public void triggerAskPassword() {

    }

    @Override
    public String getPassPhrase() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void addPresentorListener(IPresentor p) {

    }
}
