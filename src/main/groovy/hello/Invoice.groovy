package hello

import groovy.transform.ToString

import javax.persistence.*

/**
 * Created by rcapraro on 30/12/2013.
 */
@Entity(name = "INVOICES")
@ToString
class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    Double amount

    @OneToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    Order order
}
