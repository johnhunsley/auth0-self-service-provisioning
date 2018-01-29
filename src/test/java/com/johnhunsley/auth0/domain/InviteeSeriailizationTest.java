package com.johnhunsley.auth0.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 29/01/2018
 *         Time : 09:52
 */
@JsonTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InviteeSeriailizationTest {

    @Autowired
    private JacksonTester<Invitee> tester;

    @Test
    public void testSerialize() throws IOException {
        final String expected = "{\"class\":\"Invitee\",\"firstName\":\"John\",\"lastName\":\"Hunsley\",\"email\":\"jphunsley@gmail.com\",\"memberId\":\"PP702\",\"role\":\"ROLE_TEST\"}";
        Invitee invitee = new Invitee();
        invitee.setMemberId("PP702");
        invitee.setEmail("jphunsley@gmail.com");
        invitee.setFirstName("John");
        invitee.setLastName("Hunsley");
        invitee.setRole("ROLE_TEST");
        System.out.println(tester.write(invitee).getJson());
        assertThat(tester.write(invitee)).isEqualToJson(expected);
    }


}
