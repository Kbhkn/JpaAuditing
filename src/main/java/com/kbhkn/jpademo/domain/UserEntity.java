package com.kbhkn.jpademo.domain;

import com.kbhkn.jpademo.domain.audit.BaseAuditEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.security.Principal;
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
@Table(
        name = "USERS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"})
)
public class UserEntity extends BaseAuditEntity implements Principal {
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    @Column
    private String token;

    @Column(nullable = false)
    private Character isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "USER_ROLE_MAP",
            joinColumns =
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public Boolean isActive() {
        return isActive == 'Y';
    }

    public Set<ActionEntity> getAllActionsOfUser() {
        Set<ActionEntity> actions = new HashSet<>();

        roles.stream()
                .map(RoleEntity::getActions)
                .forEach(actions::addAll);

        return actions;
    }

    @Override
    public String getName() {
        return username;
    }

}
