package pl.connectis.electronicswebshop.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.connectis.electronicswebshop.persistence.model.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);

}
