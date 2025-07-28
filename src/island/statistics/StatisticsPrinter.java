package island.statistics;

import island.map.Island;
import island.map.Location;
import island.model.*;

import java.util.HashMap;
import java.util.Map;

public class StatisticsPrinter {
    public static void print(Island island) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                String name = animal.getClass().getSimpleName();
                counts.put(name, counts.getOrDefault(name, 0) + 1);
            }
            for (Plant plant : location.getPlants()) {
                String name = plant.getClass().getSimpleName();
                counts.put(name, counts.getOrDefault(name, 0) + 1);
            }
        }

        System.out.println("Statistic:");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    public static void PrintAnimalBirth(Animal a, Location l) {
        System.out.println(a + " was born in location " + l);
    }

    public static void PrintAnimalEaten(Animal prey, Animal predator, Location l) {
        System.out.println(prey + " was eaten by a predator " + predator + " in location " + l);
    }

    public static void PrintAnimalDeath(Animal a, Location l) {
        System.out.println(a + " died of hunger in location " + l);
    }

    public static void PrintAnimalMove(Animal a, Location l1, Location l2) {
        System.out.println(a + " moved from " + l1 + " to " + l2);
    }


}
