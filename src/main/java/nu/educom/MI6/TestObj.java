package nu.educom.MI6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="agents")
public class TestObj {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getLicenceToKill() {
        return licenceToKill;
    }

    public void setLicenceToKill(boolean licenceToKill) {
        this.licenceToKill = licenceToKill;
    }

    public LocalDate getLicenceToKillEnd() {
        return licenceToKillEnd;
    }

    public void setLicenceToKillEnd(LocalDate licenceToKillEnd) {
        this.licenceToKillEnd = licenceToKillEnd;
    }
}
