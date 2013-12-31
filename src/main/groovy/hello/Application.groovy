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
        CustomerRepository repository = context.getBean(CustomerRepository.class);

        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"))
        repository.save(new Customer("Chloe", "O'Brian"))
        repository.save(new Customer("Kim", "Bauer"))
        repository.save(new Customer("David", "Palmer"))
        repository.save(new Customer("Michelle", "Dessler"))
    }

}
