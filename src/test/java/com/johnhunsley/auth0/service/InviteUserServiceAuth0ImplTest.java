package com.johnhunsley.auth0.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.UsersEntity;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import com.johnhunsley.auth0.domain.InvitationStatus;
import com.johnhunsley.auth0.domain.Invitee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 29/01/2018
 *         Time : 10:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InviteUserServiceAuth0ImplTest {

    @MockBean
    private ManagementAPI mgmt;

    @MockBean
    private AuthAPI auth;

    @Autowired
    private InviteUsersService inviteUsersService;

    @Test
    public void testInviteUsers() throws Exception {
        List<Invitee> invites = new ArrayList<>();
        Invitee invitee = new Invitee();
        invitee.setMemberId("PP702");
        invitee.setEmail("jphunsley@gmail.com");
        invitee.setFirstName("John");
        invitee.setLastName("Hunsley");
        invitee.setRole("ROLE_TEST");
        invites.add(invitee);

        UsersEntity entity = Mockito.mock(UsersEntity.class);
        Request<User> request = Mockito.mock(Request.class);
        given(mgmt.users()).willReturn(entity);
        given(entity.create(anyObject())).willReturn(request);
        User mockUser = Mockito.mock(User.class);
        when(mockUser.getId()).thenReturn("1");
        given(request.execute()).willReturn(mockUser);
        given(auth.resetPassword(anyString(), anyString())).willReturn(request);

        Set<InvitationStatus> results =  inviteUsersService.inviteUsers(invites);
        assertTrue(results.size() == 1);
        assertTrue(results.iterator().next().getStatus() == InvitationStatus.SUCCESS);
    }

}
