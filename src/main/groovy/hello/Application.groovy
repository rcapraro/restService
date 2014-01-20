package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

import javax.sql.DataSource

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
/**
 * Created by rcapraro on 30/12/2013.
 */
public class Application {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean()
        lef.setDataSource(dataSource)
        lef.setJpaVendorAdapter(jpaVendorAdapter)
        lef.setPackagesToScan("hello")
        lef
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(true)
        hibernateJpaVendorAdapter.setGenerateDdl(true)
        hibernateJpaVendorAdapter.setDatabase(Database.H2)
        hibernateJpaVendorAdapter
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        new JpaTransactionManager()
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        // save a couple of customers
        def builder = new ObjectGraphBuilder()
        builder.classNameResolver = "hello.model"


        def product1 = builder.product(name: 'Basic Optical', description: 'Microsoft mouse Basic optical', price: 10)
        def product2 = builder.product(name: 'G400s', description: 'Logitech mouse G400s', price: 30)
        def product3 = builder.product(name: 'Razer Deathadder', description: 'Razer Deathadder 2013', price: 60)
        def product4 = builder.product(name: 'Razer Taipan', description: 'Razer Taipan ambidextrous mouse', price: 80)

        productRepository.save(product1)
        productRepository.save(product2)
        productRepository.save(product3)
        productRepository.save(product4)

        def customer1 = builder.customer(
                firstName: 'Jack',
                lastName: 'Bauer',
                street: '415 Downing Street',
                city: 'New York',
                zipCode: '58745',
                email: 'jack.bauer@cat.com'
        )
                {
                    order(totalPrice: 40, description: 'Order of 04/01/2014', orderDate: new Date(), products: [product1, product2])
                }

        def customer2 = builder.customer(
                firstName: 'Michael',
                lastName: 'Bauer',
                street: '200 Bridge Avenue',
                city: 'San Francisco',
                zipCode: '23444',
                email: 'michael.bauer@hotmail.com'
        )
                {
                    order(totalPrice: 60, description: 'Order of 06/01/2014', orderDate: new Date(), products: [product3])
                }

        def customer3 = builder.customer(
                firstName: 'John',
                lastName: 'Stevenson',
                street: '589 Hall Avenue',
                city: 'Boston',
                zipCode: '78542',
                email: 'john_s80@yahoo.com'
        )
                {
                    order(totalPrice: 90, description: 'Order of 09/01/2014', orderDate: new Date(), products: [product1, product4])
                }

        customerRepository.save(customer1)
        customerRepository.save(customer2)
        customerRepository.save(customer3)

    }

}
