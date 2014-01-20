package hello.model
import com.fasterxml.jackson.annotation.JsonBackReference
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "ORDERS")
@ToString(excludes = "customer")
@EqualsAndHashCode
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    Double totalPrice

    String description

    Date orderDate

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "order", targetEntity = Invoice.class)
    Invoice invoice

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    Customer customer

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ORDER_DETAIL",
            joinColumns =
            @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    )
    List<Product> products = new ArrayList<Product>()

}
