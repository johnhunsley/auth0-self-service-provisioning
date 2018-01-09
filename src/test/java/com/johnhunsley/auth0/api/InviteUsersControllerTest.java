package com.johnhunsley.auth0.api;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.UsersEntity;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 09/01/2018
 *         Time : 14:38
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = InviteUsersControllerTest.class, secure = false)
public class InviteUsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagementAPI mgmt;

    @MockBean
    private AuthAPI auth;

    @Test
    public void testInviteUsers() throws Exception {
        UsersEntity entity = Mockito.mock(UsersEntity.class);
        Request<User> request = Mockito.mock(Request.class);
        given(mgmt.users()).willReturn(entity);
        given(entity.create(anyObject())).willReturn(request);
        given(request.execute()).willReturn(Mockito.mock(User.class));
        given(auth.resetPassword(anyString(), anyString())).willReturn(null);
        mockMvc.perform(post("/app/invite").contentType("application/json").content("[\"bob@gmail.com\", \"fred@gmail.com\"]"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}
