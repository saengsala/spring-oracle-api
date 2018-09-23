package com.spring.oracle.api.springoracleapi.entity;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_ID_SEQ", allocationSize = 1, name = "MEMBER_ID_SEQ")
    private int memberId;

    private int operatorId;

    private String applicationId;

    private String modifiedServiceCode;

    private String firstName;

    private String lastName;

    private String   memberType;

    private String memberStatus;

    public Member(){
        super();
    }

    public Member(int memberId
            , int operatorId
            , String applicationId
            , String modifiedServiceCode
            , String firstName
            , String lastName
            , String memberType
            , String memberStatus) {
        this.memberId = memberId;
        this.operatorId = operatorId;
        this.applicationId = applicationId;
        this.modifiedServiceCode = modifiedServiceCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
        this.memberStatus = memberStatus;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getModifiedServiceCode() {
        return modifiedServiceCode;
    }

    public void setModifiedServiceCode(String modifiedServiceCode) {
        this.modifiedServiceCode = modifiedServiceCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", sysCreationDate=" + super.getSysCreationDate() +
                ", sysUpdateDate=" + super.getSysUpdateDate() +
                ", operatorId=" + operatorId +
                ", applicationId='" + applicationId + '\'' +
                ", modifiedServiceCode='" + modifiedServiceCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", memberType=" + memberType +
                ", memberStatus='" + memberStatus + '\'' +
                '}';
    }
}
