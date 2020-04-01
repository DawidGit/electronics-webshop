package pl.connectis.electronicswebshop.persistence.model;

import javax.persistence.*;
import java.util.Collection;



@Entity
@Table(name="users")
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

    public User(){}

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

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
