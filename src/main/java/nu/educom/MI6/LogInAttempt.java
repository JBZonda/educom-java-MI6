package nu.educom.MI6;

public class LogInAttempt {
    String agentNumber;
    String secretSentence;

    public LogInAttempt(String aN, String sS){
        agentNumber = aN;
        secretSentence = sS;
    }

    public String getAgentNumber(){
        return agentNumber;
    }

    public String getSecretSentence() {
        return secretSentence;
    }
}
