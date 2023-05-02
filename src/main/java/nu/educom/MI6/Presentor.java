package nu.educom.MI6;

public class Presentor implements IPresentor {
    IView view;
    MI6Model mdl;
    boolean running = true;
    Presentor(IView view, MI6Model model) {
        this.view = view;
        mdl = model;
    }

    public void run() {
        while (running) {
            view.triggerAskLogin();
            view.triggerAskPassword();
        }
    }
    @Override
    public void handleLogin() {


    }

    @Override
    public void handlePassword() {

    }
}
