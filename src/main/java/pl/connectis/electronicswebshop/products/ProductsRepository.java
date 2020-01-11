package pl.connectis.electronicswebshop.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product,Long> {



}
