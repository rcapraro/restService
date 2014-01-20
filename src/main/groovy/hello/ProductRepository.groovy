package hello

import hello.model.Product
import org.springframework.data.repository.CrudRepository

/**
 * Created by rcapraro on 16/01/2013.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
