package pl.connectis.electronicswebshop.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
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
