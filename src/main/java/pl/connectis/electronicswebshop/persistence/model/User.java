package pl.connectis.electronicswebshop.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    private String email;
    private String firstName;
    private String lastName;

    public User(String password, String username, Collection<Role> roles, String email) {
        this.roles = roles;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public User(String password, String username, Collection<Role> roles, String email, String firstName, String lastName) {
        this.roles = roles;
        this.password = password;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
