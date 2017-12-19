package cz.muni.fi.pa165.mvc.controllers;

import dto.TireDTO;
import dto.TireManufacturerDTO;
import facade.CustomerFacade;
import facade.TireFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

/**
 * Provides the public shopping interface.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingControler {

    final static Logger log = LoggerFactory.getLogger(ShoppingControler.class);

    @Inject
    private TireFacade tireFacade;

    /**
     * Shows all categories and products.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/show")
    public String list(Model model) {
        
        List<TireDTO> tires = tireFacade.getAllTires();
        model.addAttribute("tires", tires);
        
        Set<TireManufacturerDTO> manufacturers = tireFacade.getAllTireManufacturers();
        model.addAttribute("manufacturers", manufacturers);
        
        Map<TireManufacturerDTO, List<TireDTO>> tiresByManufs = new HashMap<>();
        for (TireDTO t : tires){
            tiresByManufs.put(t.getManufacturer(), tireFacade.findTireByProperties(t.getManufacturer(), null));
        }        
        model.addAttribute("tiresByManufs", tiresByManufs);

        //forward to /shopping/show.jsp
        return "shopping/show";
    }

    /**
     * Shows product detail.
     *
     * @param id    product id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        log.info("DOBRA KOKOTINA");
        log.debug("product({})", id);
        model.addAttribute("product", tireFacade.getByID(id));
        return "shopping/product";
    }

    /**
     * Shows category detail.
     *
     * @param id    category id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/category/{id}")
    public String category(@PathVariable long id, Model model) {
        /*log.debug("category({})", id);
        CategoryDTO categoryDTO = categoryFacade.getCategoryById(id);
        model.addAttribute("category", categoryDTO);
        model.addAttribute("products", productFacade.getProductsByCategory(categoryDTO.getName()));*/
        return "shopping/category";
    }
}
