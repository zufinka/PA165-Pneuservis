package cz.muni.fi.pa165.pneuservis.mvc.controllers;

import facade.OrderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    final static Logger log = LoggerFactory.getLogger(ShoppingControler.class);

    @Autowired
    private OrderFacade categoryFacade;
}
