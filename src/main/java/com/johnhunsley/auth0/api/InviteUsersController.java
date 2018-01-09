package com.johnhunsley.auth0.api;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 09/01/2018
 *         Time : 09:57
 */
@RestController
@RequestMapping("app/invite")
public class InviteUsersController {

    @Autowired
    private ManagementAPI mgmt;

    @Autowired
    private AuthAPI auth;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, consumes = "test/html")
    public ResponseEntity<Set<InvitationStatus>> inviteUsers(@RequestBody String[] emails) {
        Set<InvitationStatus> results = new HashSet<>();

        for(String email : emails) {
            User data = new User();
            Request<User> mgmtRequest = mgmt.users().create(data);

            try {
                User response = mgmtRequest.execute();

                if(response.getId() != null) {
                    Request authRequest = auth.resetPassword(email, "Username-Password-Authentication");
                    authRequest.execute();
                    results.add(new InvitationStatus(email, InvitationStatus.SUCCESS));
                }
            } catch (APIException exception) {
                System.out.println(exception.getMessage());
                results.add(new InvitationStatus(email, InvitationStatus.FAILURE, exception.getMessage()));

            } catch (Auth0Exception exception) {
                System.out.println(exception.getMessage());
                results.add(new InvitationStatus(email, InvitationStatus.FAILURE, exception.getMessage()));
            }
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
