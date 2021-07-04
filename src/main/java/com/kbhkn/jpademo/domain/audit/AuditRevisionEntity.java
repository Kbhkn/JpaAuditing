package com.kbhkn.jpademo.domain.audit;

import com.kbhkn.jpademo.configurations.audit.AuditingRevisionListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Entity
@Getter
@Setter
@Table(name = "REVINFO", schema = "ARCHIVE_SCHEMA")
@RevisionEntity(AuditingRevisionListener.class)
public class AuditRevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int rev;

    @Column(name = "REVTSTMP")
    @RevisionTimestamp
    private long timestamp;

    @Column
    private String username;

    // Add custom other definitions, it can be manage via AuditingRevisionListener

}