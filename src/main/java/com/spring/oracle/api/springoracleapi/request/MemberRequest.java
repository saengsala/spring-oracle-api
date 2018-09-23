package com.spring.oracle.api.springoracleapi.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.oracle.api.springoracleapi.entity.Member;


public class MemberRequest extends Member {

    @JsonCreator
    public MemberRequest( @JsonProperty("memberId") int memberId
            , @JsonProperty("operatorId") int operatorId
            , @JsonProperty("applicationId") String applicationId
            , @JsonProperty("modifiedServiceCode") String modifiedServiceCode
            , @JsonProperty("firstName") String firstName
            , @JsonProperty("lastName") String lastName
            , @JsonProperty("memberType") String memberType
            , @JsonProperty("memberStatus") String memberStatus) {
        super(memberId
                , operatorId
                , applicationId
                , modifiedServiceCode
                , firstName
                , lastName
                , memberType
                , memberStatus);
    }

}
