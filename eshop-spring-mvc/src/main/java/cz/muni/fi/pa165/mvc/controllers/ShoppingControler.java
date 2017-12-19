package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

        Set<VehicleTypeEnum> vehicle = new HashSet<>();
        Set<Integer> width = new HashSet<>();
        Set<Integer> aspectRatio = new HashSet<>();
        Set<Integer> diameter = new HashSet<>();
        Set<Integer> loadIndex = new HashSet<>();
        Set<SpeedClassEnum> speed = new HashSet<>();
        Set<SeasonEnum> season = new HashSet<>();

        Map<TireManufacturerDTO, List<TireDTO>> tiresByManufs = new HashMap<>();
        for (TireDTO t : tires) {
            tiresByManufs.put(t.getManufacturer(), tireFacade.findTireByProperties(t.getManufacturer(), null));
            TirePropertiesDTO tp = t.getTireProperties();
            vehicle.add(tp.getVehicleType());
            width.add(tp.getWidth());
            aspectRatio.add(tp.getAspectRatio());
            diameter.add(tp.getDiameter());
            loadIndex.add(tp.getLoadIndex());
            speed.add(tp.getSpeedClass());
            season.add(tp.getSeason());
        }
        model.addAttribute("tiresByManufs", tiresByManufs);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("width", width);
        model.addAttribute("aspectRatio", aspectRatio);
        model.addAttribute("diameter", diameter);
        model.addAttribute("loadIndex", loadIndex);
        model.addAttribute("speed", speed);
        model.addAttribute("season", season);

        //forward to /shopping/show.jsp
        return "shopping/show";
    }

    /**
     * Shows product detail.
     *
     * @param id product id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        log.debug("product({})", id);
        model.addAttribute("product", tireFacade.getByID(id));
        return "shopping/product";
    }

   
    @RequestMapping("/filter")
    public String filter(Model model) {
           
            List<TireDTO> tires = tireFacade.getAllTires();
            model.addAttribute("tires", tires);

        return "shopping/filter";
    }
    
/*
    //@RequestMapping("/category/{vehicle}/{width}/{aspectRatio}/{diameter}/{loadIndex}/{speed}/{season}/{manufacturer}")
    @RequestMapping("/filter")
    public String filter(/*@PathVariable VehicleTypeEnum vehicle,
            @PathVariable int width,
            @PathVariable int aspectRatio,
            @PathVariable int diameter,
            @PathVariable int loadIndex,
            @PathVariable SpeedClassEnum speed,
            @PathVariable SeasonEnum season,
            @PathVariable String name,
            Model model) {
            
            /* REPAIR
            TirePropertiesDTO tp = new TirePropertiesDTO(vehicle, width, aspectRatio, diameter, loadIndex, speed, season);
            TireManufacturerDTO tm = new TireManufacturerDTO(name);      
            
            TirePropertiesDTO tp = new TirePropertiesDTO(null, 10, 0, 10, 0, SpeedClassEnum.B, null);
            TireManufacturerDTO tm = new TireManufacturerDTO(null);      
            
            List<TireDTO> tires = tireFacade.findTireByProperties(tm, tp);
            model.addAttribute("tires", tires);

        
        return "shopping/filter";
    }
            */
}
