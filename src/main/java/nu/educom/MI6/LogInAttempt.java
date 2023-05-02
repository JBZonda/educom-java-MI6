package nu.educom.MI6;

public class LogInAttempt {
    String agentNumber;
    String passPhrase;

    public LogInAttempt(String aN, String pP){
        agentNumber = aN;
        passPhrase = pP;
    }

    public String getAgentNumber(){
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public void setSecretSentence(String secretSentence) {
        this.passPhrase = secretSentence;
    }

    public String getSecretSentence() {
        return passPhrase;
    }
}
