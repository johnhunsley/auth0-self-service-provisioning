package com.johnhunsley.auth0.domain;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 09/01/2018
 *         Time : 10:07
 */
public class InvitationStatus {
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    private String emailAddress;
    private int status;
    private String desc;

    public InvitationStatus() {}

    public InvitationStatus(String emailAddress, int status) {
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public InvitationStatus(String emailAddress, int status, String desc) {
        this.emailAddress = emailAddress;
        this.status = status;
        this.desc = desc;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
