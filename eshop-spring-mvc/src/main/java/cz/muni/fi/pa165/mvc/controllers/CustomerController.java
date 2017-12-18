package cz.muni.fi.pa165.mvc.controllers;

import dto.CustomerDTO;
import facade.CustomerFacade;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@Controller
@RequestMapping("/newcustomer")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerFacade customerFacade;

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO createCustomer(
            @PathVariable String name, @PathVariable String surname, @PathVariable String city,
            @PathVariable String street, @PathVariable String zipCode, @PathVariable String country,
            @PathVariable String email, @PathVariable String phoneNumber){
        logger.debug("createCustomer()");
        CustomerDTO customer = new CustomerDTO(name, surname, city, street, zipCode, country, email, phoneNumber);
        try {
            customerFacade.createCustomer(customer);
            return customerFacade.getCustomerById(customer.getId());
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return null;
        }
    }

}
