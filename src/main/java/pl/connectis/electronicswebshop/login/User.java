package pl.connectis.electronicswebshop.login;

import javax.persistence.*;


@Entity
@Table(name = "UserTab")
public class User {

    @Id
    @GeneratedValue

//    @Column(name = "userID")
    public int id;
//    @Column(name = "login")
    public String login;
//    @Column(name = "encryptPassword")
    public String encryptPassword;


    public User(String login, String passw) throws Exception {
        this.login = login;
        this.encryptPassword = EncryptPass.EncryptPassword(passw);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return this.encryptPassword;

    }
}
