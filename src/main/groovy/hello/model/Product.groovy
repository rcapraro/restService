package hello.model

import com.fasterxml.jackson.annotation.JsonBackReference
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*

/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "PRODUCTS")
@ToString(excludes = "orders")
@EqualsAndHashCode
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name

    String description

    Double price

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonBackReference
    List<Order> orders
}
