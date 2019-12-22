package pl.connectis.electronicswebshop.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.connectis.electronicswebshop.persistence.model.User;

@Repository("postgres")
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
}
