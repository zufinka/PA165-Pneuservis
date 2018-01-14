package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;
import facade.TireFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides the public shopping interface.
 *
 * @author Martin Kuba makub@ics.muni.cz edited for project purposes by Jakub
 * Palenik
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingControler {

    final static Logger log = LoggerFactory.getLogger(ShoppingControler.class);

    @Inject
    private TireFacade tireFacade;

    /*
    @Inject
    private HttpServletRequest request;
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

        return "shopping/show";
    }

    @RequestMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        log.debug("product({})", id);
        model.addAttribute("product", tireFacade.getByID(id));
        return "shopping/product";
    }

    @RequestMapping("/filter")
    public String filter(Model model, HttpServletRequest request) {
  
        VehicleTypeEnum vehiclType = (request.getParameter("s_vehicle").equals("")) ? null : VehicleTypeEnum.valueOf(request.getParameter("s_vehicle"));
        int width = (request.getParameter("s_width").equals("")) ? 0 : Integer.parseInt(request.getParameter("s_width"));
        int aspectRatio = (request.getParameter("s_aspectRatio").equals("")) ? 0 : Integer.parseInt(request.getParameter("s_aspectRatio"));
        int diameter = (request.getParameter("s_diameter").equals("")) ? 0 : Integer.parseInt(request.getParameter("s_diameter"));
        int loadIndex = (request.getParameter("s_loadIndex").equals("")) ? 0 : Integer.parseInt(request.getParameter("s_loadIndex"));
        SpeedClassEnum speedClass = (request.getParameter("s_speed").equals("")) ? null : SpeedClassEnum.valueOf(request.getParameter("s_speed"));
        SeasonEnum season = (request.getParameter("s_season").equals("")) ? null : SeasonEnum.valueOf(request.getParameter("s_season"));
        String manufName = (request.getParameter("s_manuf").equals("")) ? null : request.getParameter("s_manuf");

        TirePropertiesDTO tp = new TirePropertiesDTO(vehiclType, width, aspectRatio, diameter, loadIndex, speedClass, season);
        TireManufacturerDTO tm = manufName == null ? null : new TireManufacturerDTO(manufName);
        tm = null;
        
        List<TireDTO> tires = tireFacade.findTireByProperties(tm, tp);
        model.addAttribute("tires", tires);

        return "shopping/filter";
    }

}
