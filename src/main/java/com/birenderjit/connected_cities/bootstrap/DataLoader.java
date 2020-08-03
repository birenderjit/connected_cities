package com.birenderjit.connected_cities.bootstrap;

import com.birenderjit.connected_cities.Model.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final Graph cityGraph;

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    public DataLoader(Graph cityGraph) {
        this.cityGraph = cityGraph;
    }

    @Override
    public void run(String... args) throws Exception {

        if (cityGraph.getCityToNodeMap().size() == 0) {
            loadData();
        }

    }

    private void loadData() throws IOException {
        //Graph cityGraph = new Graph();
        Map<String, Set<String>> cityToNodeMap = cityGraph.getCityToNodeMap();
        logger.info("Starting to load data --");

        Resource resource = new ClassPathResource("data/city.txt");
        BufferedReader reader = null;
        try {
            InputStream inputStream = resource.getInputStream();
            reader = new BufferedReader( new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                String[] cities = line.split(",");
                String firstCity = cities[0].trim();
                String secondCity = cities[1].trim();

                //logger.info("firstCity -- " + firstCity + ", secondCity -- " + secondCity);

                Set<String> firstCityConnections = cityGraph.getCityConnections(cityToNodeMap, firstCity);
                Set<String> secondCityConnections = cityGraph.getCityConnections(cityToNodeMap, secondCity);
                firstCityConnections.add(secondCity);
                secondCityConnections.add(firstCity);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        logger.info("Data Loaded....");
        logger.info("Graph size -- " + cityToNodeMap.size());
        logger.info("Graph  -- " + cityToNodeMap.toString());


    }
}
