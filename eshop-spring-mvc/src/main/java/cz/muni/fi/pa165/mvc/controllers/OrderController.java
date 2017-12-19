package cz.muni.fi.pa165.mvc.controllers;

import facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @RequestMapping("/list")
    public String list(Model model) {

        model.addAttribute("orders", orderFacade.getAllOrders());

        return "order/list";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("order", orderFacade.getOrder(id));

        return "order/show";
    }
}
