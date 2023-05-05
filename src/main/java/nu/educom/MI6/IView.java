package nu.educom.MI6;

public interface IView {
    void showError(String msg);
    void showWelcome(String msg);
    void triggerAskLogin();
    String getServiceNumber();
    void triggerAskPassword();
    String getPassPhrase();
    void close();
    void addPresentorListener(IPresentor p);

    void showTimeOut(int totalTimeSec);
}
