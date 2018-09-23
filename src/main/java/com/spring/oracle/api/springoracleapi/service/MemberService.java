package com.spring.oracle.api.springoracleapi.service;

import com.spring.oracle.api.springoracleapi.entity.Member;
import com.spring.oracle.api.springoracleapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List getAllMembers() {
        List member = new ArrayList<>();
        memberRepository.findAll().forEach(member::add);
        return member;
    }

    public Optional<Member> getMemberById(int memberId) {
        return memberRepository.findById(memberId);
    }

    public Member setMemberInfo(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> deleteMemberById(int memberId) {
        memberRepository.deleteById(memberId);
        return memberRepository.findById(memberId);
    }

}
