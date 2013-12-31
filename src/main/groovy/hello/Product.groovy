package hello

import groovy.transform.ToString

import javax.persistence.*

/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "PRODUCTS")
@ToString
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name

    String description

    String price

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    List<Order> orderList
}
