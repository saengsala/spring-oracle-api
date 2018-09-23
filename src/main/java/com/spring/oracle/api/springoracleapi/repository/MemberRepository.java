package com.spring.oracle.api.springoracleapi.repository;

import com.spring.oracle.api.springoracleapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
}
