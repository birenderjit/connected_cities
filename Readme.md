# Connected Cities

This is a spring boot application written with Spring Boot 2.3.2.RELEASE and java 11.

The goal is to use the data/city.txt file and check if two cities are connected.
Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

List of roads is available in the data/city.txt file .
The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

**city.txt sample content is:**

  Boston, New York
  Philadelphia, Newark
  Newark, Boston
  Trenton, Albany  

It will be deployed as a Spring Boot App and expose one endpoint:
http://localhost:8080/connected?origin=city1&destination=city2

The program will respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.
Any unexpected input should result in a ’no’ response.
