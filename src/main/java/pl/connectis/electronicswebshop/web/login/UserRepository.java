package pl.connectis.electronicswebshop.web.login;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User,Long>{


    EncryptPass findPassByLogin(String login);


    User findByEmail(String email);
}