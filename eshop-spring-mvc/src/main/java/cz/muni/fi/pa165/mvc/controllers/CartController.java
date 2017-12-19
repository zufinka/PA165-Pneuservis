package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import dto.OrderDTO;
import dto.OrderItemDTO;
import dto.ServiceDTO;
import dto.TireDTO;
import facade.OrderFacade;
import facade.OrderItemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private OrderFacade orderFacade;

    @RequestMapping("/{id}")
    public String list(@PathVariable("id") Long id, Model model) {
//        List<OrderItemDTO> allOrderItems = orderFacade.getOrder(id).getOrderItems();

        /*Map<ServiceDTO, Long> orderServices = new HashMap<>();
        Map<TireDTO, Long> orderTires = new HashMap<>();
        for (OrderItemDTO orderItem : allOrderItems) {
            if(orderItem.getService() != null) {
                orderServices.put(orderItem.getService(), orderItem.getQuantity());
            } else {
                orderTires.put(orderItem.getTire(), orderItem.getQuantity());
            }
        }
        model.addAttribute("orderServices", orderServices);*/

        model.addAttribute("order", orderFacade.getOrder(id));

        return "cart/show";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String setQuantity(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("order") OrderDTO data,
                              BindingResult bindingResult, Model model,
                              RedirectAttributes redirectAttributes,
                              UriComponentsBuilder uriComponentsBuilder) {

        /*if(bindingResult.hasErrors()) {
            return uriComponentsBuilder.path("/cart/{id}/edit").queryParam("id", id).toString();
        }*/

        List<OrderItemDTO> newOrderItems = new ArrayList<>();

        for (OrderItemDTO orderItemDTO : data.getOrderItems()) {
            if (orderItemDTO.getQuantity() > 0) {
                newOrderItems.add(orderItemDTO);
            }
        }

        data.setOrderItems(newOrderItems);
        orderFacade.update(data);

        redirectAttributes.addFlashAttribute("alert_success", "Edit successful!");

        return "redirect:" + uriComponentsBuilder.path("/{id}");
    }
}