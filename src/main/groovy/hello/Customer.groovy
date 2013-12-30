package hello

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id
    private String firstName
    private String lastName

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }

}
