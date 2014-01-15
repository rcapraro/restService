package hello

import hello.model.Customer
import org.springframework.data.repository.CrudRepository

/**
 * Created by rcapraro on 30/12/2013.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName)
}
