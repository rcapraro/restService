package hello.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "CUSTOMERS")
@ToString
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = Order.class, fetch = FetchType.LAZY)
    Collection<Order> orders = new ArrayList<Order>();

    Customer() {
    }

    Customer(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }
}

