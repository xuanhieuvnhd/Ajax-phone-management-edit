package com.controller;

import com.model.Smartphone;
import com.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/smartphones")
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping(value = "/create")
    public ModelAndView createSmartphonePage() {
        ModelAndView modelAndView = new ModelAndView("phones/new-phone");
        modelAndView.addObject("sPhone", new Smartphone());
        return modelAndView;
    }

    @PostMapping(value = "/create-new-phone", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone createSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneService.save(smartphone);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Smartphone> allPhones() {
        return smartphoneService.findAll();
    }

    @GetMapping("")
    public ModelAndView allPhonesPage() {
        ModelAndView modelAndView = new ModelAndView("phones/all-phones");

        modelAndView.addObject("allphones", allPhones());
        return modelAndView;
    }

    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone deleteSmartphone(@PathVariable Integer id) {
        return smartphoneService.remove(id);
    }

    @GetMapping("edit/{id}")
    public ModelAndView editSmartphonePage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("phones/edit-phone");
        Smartphone smartphone = smartphoneService.findById(id);
        modelAndView.addObject("sPhone", smartphone);
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone editSmartphone(@PathVariable int id, @RequestBody Smartphone smartphone) {
        smartphone.setId(id);
        return smartphoneService.save(smartphone);
    }
}
