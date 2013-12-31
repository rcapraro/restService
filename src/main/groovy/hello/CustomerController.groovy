package hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by rcapraro on 30/12/2013.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository

    @RequestMapping("/")
    @ResponseBody

    public String helloWorld() {
        "Hello world"
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Customer> getAllCustomers() {
        customerRepository.findAll()
    }
}
