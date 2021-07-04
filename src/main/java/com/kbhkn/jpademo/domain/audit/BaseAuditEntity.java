package com.kbhkn.jpademo.domain.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Who created it, who changed it, when?.
 *
 * @author Hakan KABASAKAL, 30-Mar-20
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /* Who creates this row? */
    @CreatedBy
    @Column(nullable = false, updatable = false)
    protected String createdBy;

    /* When was this row created? */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    /* Who updates this row? */
    @Column
    @LastModifiedBy
    protected String lastModifiedBy;

    /* When was this row updated? */
    @Column
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
