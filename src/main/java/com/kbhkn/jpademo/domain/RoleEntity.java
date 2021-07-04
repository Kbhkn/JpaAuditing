package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Getter
@Setter
@Entity
@Audited // It uses _AUDIT_LOG tables via envers.
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
// Don't use inheritance and only include marked columns.
@Table(
        name = "ROLES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"CODE", "NAME"})
)
public class RoleEntity extends BaseAuditEntity implements GrantedAuthority {
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String code;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "ROLE_ACTION_MAP",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTION_ID", referencedColumnName = "ID")
    )
    private Set<ActionEntity> actions = new HashSet<>();

    @Override
    public String getAuthority() {
        return code;
    }
}
