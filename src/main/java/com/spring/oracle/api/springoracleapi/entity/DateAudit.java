package com.spring.oracle.api.springoracleapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"sysCreationDate", "sysUpdateDate"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Instant sysCreationDate;

    @LastModifiedDate
    private Instant sysUpdateDate;

    public Instant getSysCreationDate() {
        return sysCreationDate;
    }

    public void setSysCreationDate(Instant sysCreationDate) {
        this.sysCreationDate = sysCreationDate;
    }

    public Instant getSysUpdateDate() {
        return sysUpdateDate;
    }

    public void setSysUpdateDate(Instant sysUpdateDate) {
        this.sysUpdateDate = sysUpdateDate;
    }

}
