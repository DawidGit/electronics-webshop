package pl.connectis.electronicswebshop.login;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoginRepository extends CrudRepository<User,Long>{


    EncryptPass findPassByLogin(String login);






}