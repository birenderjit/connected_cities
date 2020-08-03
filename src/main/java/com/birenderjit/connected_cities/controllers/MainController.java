package com.birenderjit.connected_cities.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.birenderjit.connected_cities.Model.Graph;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    private Graph cityGraph;

    public MainController(Graph cityGraph) {
        this.cityGraph = cityGraph;
    }

    @RequestMapping("/connected")
    public String checkIfConnected(Model model, @RequestParam(name = "origin") String origin,
                                   @RequestParam(name = "destination") String destination) {

        String isConnected = "";
        logger.info("--- Main Controller ---");
        logger.info("Graph size -- " + cityGraph.getCityToNodeMap().size());
        /*logger.info("origin -- " + origin);
        logger.info("destination -- " + destination);*/
        if (origin.isBlank() || origin.isEmpty()  || destination.isBlank() || destination.isEmpty()) {
            isConnected = "no";
        } else {
            boolean result = cityGraph.isConnected(cityGraph.getCityToNodeMap(), origin, destination);
            if (result) {
                logger.info(origin + " and " + destination + "are connected.");
                isConnected = "yes";
            } else {
                logger.info(origin + " and " + destination + "are Not connected.");
                isConnected = "no";
            }
        }
        model.addAttribute("isConnected", isConnected);
        return "connected";
    }

}
