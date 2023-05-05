package nu.educom.MI6;

import java.time.LocalDateTime;

public class LogInAttempt {
    int id;
    int serviceNumber;
    LocalDateTime loginTime;
    boolean success;

    public LogInAttempt(int id, int serviceNumber, LocalDateTime loginTime, boolean success){
        this.id = id;
        this.serviceNumber = serviceNumber;
        this.loginTime = loginTime;
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}
