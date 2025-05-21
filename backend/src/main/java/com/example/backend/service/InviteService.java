package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.contant.Status;
import com.example.backend.model.DAO.InviteDAO;
import com.example.backend.model.Invite;
import org.jdbi.v3.core.Jdbi;

public class InviteService {
    private final InviteDAO inviteDAO;

    public InviteService(Jdbi jdbi) {
        this.inviteDAO = jdbi.onDemand(InviteDAO.class);
    }


    public Integer create(Invite invite) {
        return inviteDAO.addInvite(invite);
    }

    public static void main(String[] args) {
        InviteService inviteService = new InviteService(DBConnection.getJdbi());
        Invite invite = new Invite(null, "hung@gmail.com", "QuocHung", 2, Status.PENDING, 1747807284L, 1747807284L );

        System.out.println(inviteService.create(invite));
    }
}
