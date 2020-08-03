package com.birenderjit.connected_cities.Model;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Graph {

    // The keys in the map are the cities that we have available.
    // The value is a set of string, a set of cities that this city is connected to
    // So the strings are the nodes, the name of the cities.
    // If there is a key-value pair (or key to set pair) then those cities are connected
    // For each line from the city.txt file, we add two key-value pairs for both directions of possible traversal
    private Map<String, Set<String>> cityToNodeMap;

    public Graph() {
        cityToNodeMap = new HashMap<String, Set<String>>();
    }

    public Map<String, Set<String>> getCityToNodeMap() {
        return this.cityToNodeMap;
    }

    /**
     *
     * @param map The Map representation of the graph of nodes of cities
     * @param city The key to check, if already present return the HashSet, else create a new one
     * @return return the list of cities as a Hashset for the given city
     */
    public  Set<String> getCityConnections(Map<String, Set<String>> map, String city) {
        if (!map.containsKey(city)) {
            map.put(city, new HashSet<String>());
        }
        return map.get(city);
    }

    /**
     *
     * @param cityToNodeMap The Graph representation of the various cities as nodes
     * @param city1 Origin city
     * @param city2 Destiation city
     * @return true if the there is a path and false if there is no path between the cities
     */
    public  boolean isConnected(Map<String, Set<String>> cityToNodeMap, String city1, String city2) {
        boolean isFound = city1.equals(city2);
        if (cityToNodeMap.containsKey(city1) && cityToNodeMap.containsKey(city2)) {

            // using a Queue, to implement Breadth First Search
            // find the shortest path between two cities
            Queue<String> citiesToVisit = new LinkedList<String>();

            // keep a set of the cities already visited. This prevents BFS from looping in
            // cycles and allows the BFS to terminate if no path can be found after exploring all reachable nodes
            Set<String> citiesAlreadyVisited = new HashSet<String>();

            citiesToVisit.add(city1);

            while (!citiesToVisit.isEmpty() && !isFound) {
                String city = citiesToVisit.poll();
                isFound = city.equals(city2);

                Set<String> possibleConnections = cityToNodeMap.get(city);
                for (String possibleCity : possibleConnections) {
                    if (!citiesAlreadyVisited.contains(possibleCity)) {
                        citiesToVisit.add(possibleCity);
                        citiesAlreadyVisited.add(possibleCity);
                    }
                }
            }
        }

        return isFound;
    }
}
