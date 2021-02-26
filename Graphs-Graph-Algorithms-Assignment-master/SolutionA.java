import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Road {
    public String city1;
    public String city2;
    public Road(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }
}
class RoadMap {
    Map<String, Set<Road>> roadMap = new HashMap<String, Set<Road>>();
    //This function helps to get all the cities in the graph
    public Set<String> getAllCities() {
        return this.roadMap.keySet();
    }
    //This function will read the input 
    public void readLine(String line) {
        String[] csv = line.split(",");
        String city1 = csv[0];
        String city2 = csv[1];
        addRoad(city1, city2);
    }
    private void addCity(String city) {
        this.roadMap.put(city, new HashSet<Road>());
    }
    public Set<Road> getAllOutgoingRoads(String node) {
        return this.roadMap.get(node);
    }
     /*    This Function is used my By me to check what is in hashmap roadbb  
    public void print(){

        for (Map.Entry<String, Set<Road>> entry : roadMap.entrySet())
        {
            String x=(String)entry.getKey();
                  System.out.print("Key ->"+x+" ");
            Set<Road> t=getAllOutgoingRoads(x);
                  System.out.print("Values->");
               for(Road u:t)
               {
                  System.out.print(u.city1+" and "+u.city2+" ");
               }
                  System.out.println();
        }  
    }
    */

    //This function will add both outgoing and incoming roads between two cities
    private void addRoad(String city1, String city2) {
        Road road1 = new Road(city1, city2);
        Road road2 = new Road(city2, city1);
        if (!this.roadMap.containsKey(city1)) {
            addCity(city1);
        }
        if (!this.roadMap.containsKey(city2)) {
            addCity(city2);
        }
        this.roadMap.get(city1).add(road1);
        this.roadMap.get(city2).add(road2);
    }
    //This function will return all the outgoing roads from a city
}
public class GraphAssignment 
 {
    static RoadMap roadMap = new RoadMap();
    public static void readMap(Scanner scanner) {
        while (true) {
            String mapLine = scanner.nextLine();
            if (mapLine.equals("")) {
                break;
            }
            roadMap.readLine(mapLine);
        }
        System.out.println("Read map");
    }
     public static void findAnyRouteToCity(String source, String destination) 
      {
        Map<String,Integer> check = new HashMap<>();                                   // It is used to check visited City

        Queue<String> queue = new LinkedList<>();                                   //use for iterate city one by one

        for (String city : roadMap.getAllCities())                                //set keys -> value to 0
             check.put(city,0);

        Map<String, String> path = new HashMap<String, String>();              //store reversed values in map 
        check.put(source,1);                                                  // set value of source to Key
        queue.add(source);                                                   //  source is added in queue
        while (!queue.isEmpty())                                            //   loop execute till empty
           {
            String City = queue.remove();
            for (Road road : roadMap.getAllOutgoingRoads(City))            //get set<Road> value of a keys
               {
                if (check.get(road.city2)!=1)                              //if it is not visited
                   {
                    queue.add(road.city2);
                    path.put(road.city2,City);                            //map insert value
                    check.put(road.city2,1);                              //set visited values to 1
                   }
                }
              }
        if (check.get(destination)!=1)                                          // if it is not visited inside the queue loop
        {
            System.out.println("Not Reachable");
        } 
        else 
            {
            String City = destination;
            String result = destination;
            while (!City.equals(source))                                          // iterate from destination to source
               {
                City = path.get(City);
                result = City + " -> " + result;                                   // add value in String result in reverse order
               }
            System.out.println(result);
             }
    }
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Map");
        readMap(scanner);
        // roadMap.print(); 
         System.out.println("Enter the source ");
         String source = scanner.nextLine();
         System.out.println("Enter the destination ");
         String destination = scanner.nextLine();
         System.out.println("The route from "+source+" to "+destination+" is");
         findAnyRouteToCity(source, destination);
    }

}
