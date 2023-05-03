package nu.educom.MI6;

import java.time.LocalDate;

public class Agent {
    int id;
    int serviceNumber;
    String passPhrase;
    boolean active;
    boolean licenceToKill;
    LocalDate licenceToKillEnd;

    public Agent(int id, int serviceNumber, String passPhrase, boolean active, boolean licenceToKill, LocalDate licenceToKillEnd){
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.passPhrase = passPhrase;
        this.active = active;
        this.licenceToKill = licenceToKill;
        this.licenceToKillEnd = licenceToKillEnd;
    }

    public int getId() {
        return id;
    }
    public int getServiceNumber() {
        return serviceNumber;
    }
    public String getPassPhrase() {
        return passPhrase;
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasLicenceToKill() {
        return licenceToKill;
    }

    public LocalDate getLicenceToKillEnd() {
        return licenceToKillEnd;
    }

    public void fullPrint() {
        System.out.println(getServiceNumber()+"-" + getPassPhrase() + "-" + hasLicenceToKill());
    }

}
