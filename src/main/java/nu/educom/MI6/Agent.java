package nu.educom.MI6;

import java.time.LocalDate;
import javax.persistence.*;
@Entity
@Table(name="agents")
public class Agent {
    @Id
    int id;
    @Column(name="service_number")
    int serviceNumber;
    @Column(name="pass_phrase")
    String passPhrase;
    @Column(name="active")
    boolean active;
    @Column(name="licence_to_kill")
    boolean licenceToKill;
    @Column(name="licence_to_kill_end")
    LocalDate licenceToKillEnd;

    public Agent(int id, int serviceNumber, String passPhrase, boolean active, boolean licenceToKill, LocalDate licenceToKillEnd){
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.passPhrase = passPhrase;
        this.active = active;
        this.licenceToKill = licenceToKill;
        this.licenceToKillEnd = licenceToKillEnd;
    }

    public Agent(){

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

    public boolean getActive() {
        return active;
    }

    public boolean getLicenceToKill() {
        return licenceToKill;
    }

    public LocalDate getLicenceToKillEnd() {
        return licenceToKillEnd;
    }

    public void fullPrint() {
        System.out.println(getServiceNumber()+"-" + getPassPhrase() + "-" + getLicenceToKill());
    }

}
