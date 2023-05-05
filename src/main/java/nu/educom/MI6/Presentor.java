package nu.educom.MI6;

import java.time.Duration;

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
            if (mdl.isInteger(serviceNumber)) {
                mdl.saveLogInAttempt(serviceNumber,false);
            }
            view.showError("ACCESS DENIED!");
            return;
        }
        if (mdl.needTimeOut(Integer.parseInt(serviceNumber))) {
            int timeOut = mdl.getTimeOut(Integer.parseInt(serviceNumber));
            if (! (timeOut < 0)){
                view.showTimeOut(timeOut);
                return;
            }
        }

        mdl.setLogInAgent(serviceNumber);
        view.triggerAskPassword();

    }

    @Override
    public void handlePassword() {
        String passPhrase = view.getPassPhrase();
        String serviceNumber = view.getServiceNumber();
        if (!mdl.validPassPhrase(serviceNumber,passPhrase)){
            mdl.saveLogInAttempt(serviceNumber,false);
            view.showError("ACCESS DENIED!");
        } else if (mdl.inLoggedInList()) {
            view.showError("You are already logged in");
        } else {
            mdl.addLoggedINList(mdl.getLogInAgent());
            mdl.saveLogInAttempt(serviceNumber,true);
            view.showWelcome(String.format("Welcome Agent %03d\n asdasfafsasf asdsfafasfsaf \n adasfasfsfafs \n adafasf \n adawfafafae \n afaavavddvssdvsdv \n fffffffff", mdl.getLogInAgent()));
        }
    }
}
