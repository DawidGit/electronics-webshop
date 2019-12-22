package pl.connectis.electronicswebshop.web.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
}
