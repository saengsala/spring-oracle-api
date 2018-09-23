package com.spring.oracle.api.springoracleapi.controller;

import com.spring.oracle.api.springoracleapi.entity.Member;
import com.spring.oracle.api.springoracleapi.service.MemberService;
import com.spring.oracle.api.springoracleapi.request.MemberRequest;
import com.spring.oracle.api.springoracleapi.response.MemberResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createMember(@RequestBody MemberRequest memberRequest) {
        logger.debug("Create new member");
        Member member = new Member();
        member.setFirstName(memberRequest.getFirstName());
        member.setLastName(memberRequest.getLastName());
        member.setMemberType(memberRequest.getMemberType());
        member.setMemberStatus(memberRequest.getMemberStatus());
        member.setOperatorId(memberRequest.getOperatorId());
        member.setApplicationId(memberRequest.getApplicationId());
        Member memberResponse = memberService.setMemberInfo(member);
        if (memberService.getMemberById(memberResponse.getMemberId()).equals(Optional.empty())) {
            logger.error("Member can't create.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Can't create member"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberResponse(true, "Member has created successfully", memberResponse));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMember() {
        logger.debug("Fetching all members.");
        List memberResponse = memberService.getAllMembers();
        if (memberResponse.isEmpty()) {
            logger.error("Members not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Members not found."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MemberResponse(true, null, memberResponse));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    public ResponseEntity<?> getMemberId(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        logger.debug("Fetching member id {}.", id);
        Optional<Member> memberResponse = memberService.getMemberById(id);
        if (memberResponse.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Member id " + id + " not found."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MemberResponse(true, null, memberResponse));
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateMemberInfo(@RequestBody MemberRequest memberRequest) {
        logger.debug("Update member Information");
        if (  memberRequest.getOperatorId() == 0  ||
             (memberRequest.getApplicationId() == null || memberRequest.getApplicationId() == "") ||
             (memberRequest.getModifiedServiceCode() == null || memberRequest.getModifiedServiceCode() == "") ) {
            logger.debug("Invalid control field.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Invalid control field."));
        }
        Optional<Member> memberOptional = memberService.getMemberById(memberRequest.getMemberId());
        if (memberOptional.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Member id " + memberRequest.getMemberId() + " not found."));
        }
        Member memberResponse = new Member();
        memberResponse.setMemberId(memberRequest.getMemberId());
        memberResponse.setOperatorId(memberRequest.getOperatorId());
        memberResponse.setApplicationId(memberRequest.getApplicationId());
        memberResponse.setModifiedServiceCode(memberRequest.getModifiedServiceCode());
        if ( memberOptional.isPresent() ) {
            Member memberUpdate = memberOptional.get();
            memberResponse.setSysCreationDate(memberUpdate.getSysCreationDate());
            memberResponse.setFirstName(memberRequest.getFirstName() == null ?memberUpdate.getFirstName():memberRequest.getFirstName());
            memberResponse.setLastName(memberRequest.getLastName() == null ?memberUpdate.getLastName():memberRequest.getLastName());
            memberResponse.setMemberType(memberRequest.getMemberType() == null ?memberUpdate.getMemberType():memberRequest.getMemberType());
            memberResponse.setMemberStatus(memberRequest.getMemberStatus() == null ?memberUpdate.getMemberStatus():memberRequest.getMemberStatus());
        }
        memberResponse = memberService.setMemberInfo(memberResponse);
        return ResponseEntity.status(HttpStatus.OK).body(new MemberResponse(true, "Member information has updated successfully", memberResponse));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMemberById(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
        logger.debug("Delete member");
        Optional<Member> memberResponse = memberService.getMemberById(id);
        if (memberResponse.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Member id " + id + " not found."));
        }
        memberResponse = memberService.deleteMemberById(id);
        if (!memberResponse.equals(Optional.empty())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MemberResponse(false, "Can't delete member id " + id ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MemberResponse(true, "Member id " + id + " has deleted successfully"));
    }

}
