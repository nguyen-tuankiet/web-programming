package com.example.backend.service;

import com.example.backend.model.DAO.MemberDAO;
import com.example.backend.model.Member;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class MemberService {

    private final MemberDAO memberDAO;

    public MemberService(Jdbi jdbi) {
        this.memberDAO = jdbi.onDemand(MemberDAO.class);
    }

    public List<Member> getAll() {
        return memberDAO.getAll();
    }

    public void updateStatus(int id, String status) {
        memberDAO.updateStatus(id, status);
    }

    public void updateRole(int id, String role) {
        memberDAO.updateRole(id, role);
    }
}
