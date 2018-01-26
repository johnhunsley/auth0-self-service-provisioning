package com.johnhunsley.auth0.service;

import com.johnhunsley.auth0.domain.InvitationStatus;
import com.johnhunsley.auth0.domain.Invitee;

import java.util.List;
import java.util.Set;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 26/01/2018
 *         Time : 16:28
 */
public interface InviteUsersService {


    /**
     * <p>
     *     Invite the given list of {@link Invitee}s
     * </p>
     * @param invitations
     * @return {@link Set} of {@link InvitationStatus}
     * @throws Exception
     */
    Set<InvitationStatus> inviteUsers(List<Invitee> invitations) throws Exception;
}
