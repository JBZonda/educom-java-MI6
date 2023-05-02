package nu.educom.MI6;

public class Presentor implements IPresentor {
    IView view;
    MI6Model mdl;
    IPresentor pres;
    boolean running = true;
    Presentor(IView view, MI6Model model) {
        this.view = view;
        view.addPresentorListener(this);
        mdl = model;
    }

    public void run() {
        while (running) {
            view.triggerAskLogin();
        }
    }
    @Override
    public void handleLogin() {
        String serviceNumber = view.getServiceNumber();
        if (!mdl.validAgentNumber(serviceNumber)){
            view.showMessage("ACCESS DENIED!");
        } else {
            mdl.setLogInAgent(serviceNumber);
            view.triggerAskPassword();
        }
    }

    @Override
    public void handlePassword() {
        String passPhrase = view.getPassPhrase();
        if (!mdl.validPassPhrase(passPhrase)){
            mdl.addBlackList(mdl.getLogInAgent());
            view.showMessage("ACCESS DENIED!");
        } else if (mdl.inBlacklist()) {
            view.showMessage("You are blacklisted!");
        } else if (mdl.inLoggedInList()) {
            view.showMessage("You are already logged in");
        } else {
            mdl.addLogedINList(mdl.getLogInAgent());
            view.showMessage(String.format("Welcome Agent %03d\n", mdl.getLogInAgent()));
        }
    }
}
