package com.johnhunsley.auth0.api;
import com.johnhunsley.auth0.domain.InvitationStatus;
import com.johnhunsley.auth0.domain.Invitee;
import com.johnhunsley.auth0.service.InviteUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private InviteUsersService inviteUsersService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Set<InvitationStatus>> inviteUsers(@RequestBody List<Invitee> invitations) {

        try {
            Set<InvitationStatus> results = inviteUsersService.inviteUsers(invitations);
            return new ResponseEntity<>(results, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
