package nu.educom.MI6;

import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import javax.persistence.*;
@Entity
@Table(name = "login_attempts")
public class LogInAttempt {
    @Id
    int id;
    @Column(name="service_number")
    int serviceNumber;
    @Column(name="login_time")
    LocalDateTime loginTime;
    @Column(name="success")
    boolean success;

    public LogInAttempt(int id, int serviceNumber, LocalDateTime loginTime, boolean success){
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.loginTime = loginTime;
        this.success = success;
    }

    public LogInAttempt(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
