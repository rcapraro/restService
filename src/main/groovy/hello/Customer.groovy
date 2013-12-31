package hello

import groovy.transform.ToString

import javax.persistence.*

@Entity(name = "CUSTOMERS")
@ToString
/**
 * Created by rcapraro on 30/12/2013.
 */
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String firstName

    String lastName

    String street

    String city

    String zipCode

    String email

    @OneToMany(mappedBy = "customer", targetEntity = Order.class, fetch = FetchType.EAGER)
    Collection<Order> orders

    Customer() {
    }

    Customer(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }
}

