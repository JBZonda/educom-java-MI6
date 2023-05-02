package nu.educom.MI6;

public interface IView {
    void showMessage(String msg);
    void triggerAskLogin();
    String getServiceNumber();
    void triggerAskPassword();
    String getPassPhrase();
    void close();
    void addPresentorListener(IPresentor p);
}
