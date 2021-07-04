package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Also if needs it can use process-action structure.
 *
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Getter
@Setter
@Entity
@Audited // It uses _AUDIT_LOG tables via envers.
@EqualsAndHashCode(callSuper = false)
@Table(
        name = "ACTIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"URL"})
)
public class ActionEntity extends BaseAuditEntity {
    @Column(nullable = false)
    private String url;
}
