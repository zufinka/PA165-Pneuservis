package cz.fi.muni.pa165.rest.controllers;

import dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST HATEOAS Controller for Products
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link cz.fi.muni.pa165.rest.assemblers.productResourceAssembler} to create 
 * resources to be returned as ResponseEntities
 *
 */
@RestController
@RequestMapping("/products_hateoas")
@ExposesResourceFor(CustomerDTO.class)
public class ProductsControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(ProductsControllerHateoas.class);
//
//    @Inject
//    private ProductFacade productFacade;
//
//    @Inject
//    private ProductResourceAssembler productResourceAssembler;
//
//    /**
//     *
//     * Get list of products
//     * 
//     * @return HttpEntity<Resources<Resource<ProductDTO>>>
//     */
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public final HttpEntity<Resources<Resource<ProductDTO>>> getProducts() {
//        
//        logger.debug("rest getProducts({}) hateoas");
//
//        Collection<ProductDTO> productsDTO = productFacade.getAllProducts();
//        Collection<Resource<ProductDTO>> productResourceCollection = new ArrayList();
//
//        for (ProductDTO p : productsDTO) {
//            productResourceCollection.add(productResourceAssembler.toResource(p));
//        }
//
//        Resources<Resource<ProductDTO>> productsResources = new Resources<Resource<ProductDTO>>(productResourceCollection);
//        productsResources.add(linkTo(ProductsControllerHateoas.class).withSelfRel());
//
//        return new ResponseEntity<Resources<Resource<ProductDTO>>>(productsResources, HttpStatus.OK);
//
//    }
//    
//    /**
//     *
//     * Get list of products - this method also supports HTTP caching
//     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
//     * 
//     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
//     * 
//     * The conditional request can be sent with
//     * curl -i -X GET http://localhost:8080/eshop-rest/products_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
//     * 
//     * @return HttpEntity<Resources<Resource<ProductDTO>>>
//     */
//    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public final HttpEntity<Resources<Resource<ProductDTO>>> getProductsCached(WebRequest webRequest) {
//        
//        logger.debug("rest getProducts({}) hateoas cached version");
//       
//        final Collection<ProductDTO> productsDTO = productFacade.getAllProducts();
//        final Collection<Resource<ProductDTO>> productResourceCollection = new ArrayList();
//
//        for (ProductDTO p : productsDTO) {
//            productResourceCollection.add(productResourceAssembler.toResource(p));
//        }
//
//        Resources<Resource<ProductDTO>> productsResources = new Resources(productResourceCollection);
//        productsResources.add(linkTo(ProductsControllerHateoas.class).withSelfRel());
//
//        final StringBuffer eTag = new StringBuffer("\"");
//        eTag.append(Integer.toString(productsResources.hashCode()));
//        eTag.append('\"');
//        
//        if (webRequest.checkNotModified(eTag.toString())){
//            throw new ResourceNotModifiedException();
//        }
//                
//        return ResponseEntity.ok().eTag(eTag.toString()).body(productsResources);
//    }
//
//    /**
//     *
//     * Get one product according to id
//     * 
//     * @param id identifier for product
//     * @return HttpEntity<Resource<ProductDTO>>
//     * @throws ResourceNotFoundException
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public final HttpEntity<Resource<ProductDTO>> getProduct(@PathVariable("id") long id) throws Exception {
//        
//        logger.debug("rest getProduct({}) hateoas", id);
//
//        try {
//        ProductDTO productDTO = productFacade.getProductWithId(id);
//            Resource<ProductDTO> resource = productResourceAssembler.toResource(productDTO);
//            return new ResponseEntity<Resource<ProductDTO>>(resource, HttpStatus.OK);    
//        } catch (Exception ex){
//            throw new ResourceNotFoundException();
//        }
//    }
//    
//        /**
//     * Delete one product by id curl -i -X DELETE
//     * http://localhost:8080/eshop-rest/products/1
//     *
//     * @param id identifier for product
//     * @throws ResourceNotFoundException
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public final void deleteProduct(@PathVariable("id") long id) throws Exception {
//        logger.debug("rest deleteProduct({}) hateoas", id);
//        try {
//            productFacade.deleteProduct(id);
//        } catch (Exception ex) {
//            throw new ResourceNotFoundException();
//        }
//    }

}
