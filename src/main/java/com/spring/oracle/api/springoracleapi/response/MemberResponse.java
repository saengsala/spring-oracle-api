package com.spring.oracle.api.springoracleapi.response;

import com.spring.oracle.api.springoracleapi.entity.Member;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MemberResponse {
    private boolean status;
    private String message;
    private List member;

    public MemberResponse(boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public MemberResponse(boolean status, String message, Member member){
        this.status = status;
        this.message = message;
        this.member = Collections.singletonList(member);
    }

    public MemberResponse(boolean status, String message, List memberList){
        this.status = status;
        this.message = message;
        this.member = memberList;
    }

    public MemberResponse(boolean status, String message, Optional<Member> memberOptional){
        this.status = status;
        this.message = message;
        this.member = Collections.singletonList(memberOptional);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getMember() {
        return member;
    }

    public void setMember(List member) {
        this.member = member;
    }
}
