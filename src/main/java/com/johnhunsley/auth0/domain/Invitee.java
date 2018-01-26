package com.johnhunsley.auth0.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 26/01/2018
 *         Time : 16:31
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class")
public class Invitee implements Serializable {
    private static final long serialVersionUID = 100L;
    private String firstName;
    private String lastName;
    private String email;
    private String memberId;
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invitee)) return false;

        Invitee invitee = (Invitee) o;

        if (getFirstName() != null ? !getFirstName().equals(invitee.getFirstName()) : invitee.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(invitee.getLastName()) : invitee.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(invitee.getEmail()) : invitee.getEmail() != null) return false;
        return !(getMemberId() != null ? !getMemberId().equals(invitee.getMemberId()) : invitee.getMemberId() != null);

    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getMemberId() != null ? getMemberId().hashCode() : 0);
        return result;
    }
}
