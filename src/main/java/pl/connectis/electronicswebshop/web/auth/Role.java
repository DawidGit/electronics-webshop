package pl.connectis.electronicswebshop.web.auth;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "Id"))
    private Collection<Privilege> privileges;
}
