package hello.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "INVOICES")
@ToString(excludes = "order")
@EqualsAndHashCode
class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    Double amount

    @OneToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    Order order
}
