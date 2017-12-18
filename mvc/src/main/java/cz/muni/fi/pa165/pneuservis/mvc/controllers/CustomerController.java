package cz.muni.fi.pa165.pneuservis.mvc.controllers;

import cz.muni.fi.pa165.pneuservis.backend.dao.CustomerDao;
import dto.CustomerDTO;
import facade.CustomerFacade;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@Controller
@RequestMapping(ApiUris.ROOT_URI_ITEMS)
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerFacade customerFacade;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO createCustomer(@RequestBody CustomerDTO customer) throws Exception {
        logger.debug("createCustomer()");
        try {
            customerFacade.createCustomer(customer);
            return customerFacade.getCustomerById(customer.getId());
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO customer) throws Exception {
        logger.debug("updateCustomer({})", id);
        try {
            customerFacade.changeCustomer(customer);
            return customerFacade.getCustomerById(id);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("deleteCustomer({})", id);
        try {
            customerFacade.deleteCustomer(customerFacade.getCustomerById(id));
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("getCustomer({})", id);
        CustomerDTO customer = customerFacade.getCustomerById(id);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }
        return customer;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getAllCustomers() {
        logger.debug("getAllItems()");
        return customerFacade.findAllCustomers();
    }

    @RequestMapping(value = "/by_category_id/{category_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomerByEmail(@PathVariable("email") String email) throws Exception {
        logger.debug("getCustomerByEmail({})", email);
        CustomerDTO customer = customerFacade.getCustomerByEmail(email);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }
        return customer;
    }
}
