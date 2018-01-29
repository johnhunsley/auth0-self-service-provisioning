package com.johnhunsley.auth0.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.johnhunsley.auth0.domain.InvitationStatus;
import com.johnhunsley.auth0.domain.Invitee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 26/01/2018
 *         Time : 16:44
 */
@Component("inviteUsersService")
public class InviteUserServiceAuth0Impl implements InviteUsersService {

    @Value(value = "${auth0.connection}")
    private String connection;

    @Value(value = "${auth0.default.user.role}")
    private String defaultRole;

    @Autowired
    private ManagementAPI mgmt;

    @Autowired
    private AuthAPI auth;

    @Override
    public Set<InvitationStatus> inviteUsers(List<Invitee> invitations) throws Exception {
        Set<InvitationStatus> results = new HashSet<>();

        for(Invitee invitee : invitations) {
            User data = new User(connection);
            final String email  = invitee.getEmail();
            data.setEmail(email);
            data.setPassword(UUID.randomUUID().toString());
            Map<String, Object> userMetaData = new HashMap<>();
            userMetaData.put("firstName", invitee.getFirstName());
            userMetaData.put("lastName", invitee.getLastName());
            data.setUserMetadata(userMetaData);

            Map<String, Object> appMetaData = new HashMap<>();
            appMetaData.put("memberid", invitee.getMemberId());

            if(invitee.getRole() != null && invitee.getRole().length() > 0)
                appMetaData.put("role", invitee.getRole());
            else appMetaData.put("role", defaultRole);

            data.setAppMetadata(appMetaData);
            Request<User> mgmtRequest = mgmt.users().create(data);

            try {
                User response = mgmtRequest.execute();

                if(response.getId() != null) {
                    Request authRequest = auth.resetPassword(email.trim(), connection);
                    authRequest.execute();
                    results.add(new InvitationStatus(email, InvitationStatus.SUCCESS));
                }

            } catch (Auth0Exception exception) {
                exception.printStackTrace();
                results.add(new InvitationStatus(email, InvitationStatus.FAILURE, exception.getMessage()));
            }
        }

        return results;
    }
}
