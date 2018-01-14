package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.ApiUris;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import dto.TireDTO;
import facade.TireFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Products
 * this is what we are using in step1 of seminar10
 * also most of the other parts of the application are complete
 * this is only to test the REST API
 *
 * @author brossi
 * for project purposes Jakub Palenik
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_PRODUCTS)
public class ProductsController {

    final static Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @Inject
    private TireFacade tireFacade;

    /**
     * Get list of Products curl -i -X GET
     * http://localhost:8080/pa165/products
     *
     * @return ProductDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TireDTO> getProducts() {
        logger.debug("rest getProducts()");
        return tireFacade.getAllTires();
    }

    /**
     *
     * Get Product by identifier id curl -i -X GET
     * http://localhost:8080/pa165/products/1
     *
     * @param id identifier for a product
     * @return ProductDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TireDTO getProduct(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getProduct({})", id);
        TireDTO tireDTO = tireFacade.getByID(id);
        if (tireDTO != null) {
            return tireDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }
    
     /**
     * http://localhost:8080/pa165/products/purchase/
     * 
     * @param product ProductCreateDTO with required fields for creation
     * @param quantity number of tires to purchase
     * @return the created product ProductDTO
     * @throws 
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final void purchaseProduct(@RequestBody TireDTO product, @RequestBody int quantity) throws Exception {

        logger.debug("rest purchaseProduct()");
        tireFacade.purchaseTire(product.getId(), quantity);
        
    }




}
