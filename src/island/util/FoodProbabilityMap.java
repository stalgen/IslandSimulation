package island.util;

import island.map.Location;
import island.model.*;
import island.statistics.StatisticsPrinter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FoodProbabilityMap {
    private static final Map<Class<?>, Map<Class<?>, Integer>> foodMap = new HashMap<>();

    static {
        Map<Class<?>, Integer> wolfFood = new HashMap<>();
        wolfFood.put(Rabbit.class, 60);
        wolfFood.put(Mouse.class, 80);
        wolfFood.put(Duck.class, 40);
        foodMap.put(Wolf.class, wolfFood);

        Map<Class<?>, Integer> foxFood = new HashMap<>();
        foxFood.put(Rabbit.class, 70);
        foxFood.put(Mouse.class, 90);
        foxFood.put(Duck.class, 60);
        foodMap.put(Fox.class, foxFood);

        Map<Class<?>, Integer> bearFood = new HashMap<>();
        foxFood.put(Rabbit.class, 80);
        foxFood.put(Mouse.class, 10);
        foxFood.put(Duck.class, 90);
        foodMap.put(Bear.class, bearFood);
    }

    public static int getProbability(Class<?> predator, Class<?> prey){
        return foodMap.getOrDefault(predator, new HashMap<>()).getOrDefault(prey,0);
    }

    public static Map<Class<?>, Integer> getPreyProbabilities(Class<?> predatorClass) {
        return foodMap.getOrDefault(predatorClass, new HashMap<>());
    }

    public static void consumeAvailablePrey(Animal predator) {
        double eaten = 0;
        Location location = predator.getLocation();
        synchronized (location) {
            //Iterator<Animal> iterator = new ArrayList<>(location.getAnimals()).iterator();
            Iterator<Animal> iterator = location.getAnimals().iterator();
            Map<Class<?>, Integer> preyChances = getPreyProbabilities(predator.getClass());

            while (iterator.hasNext() && eaten < predator.getRemainingHunger()) {
                Animal prey = iterator.next();
                if (prey == predator) continue;
                int chance = preyChances.getOrDefault(prey.getClass(), 0);
                if (chance > 0 && ThreadLocalRandom.current().nextInt(100) < chance) {
                    StatisticsPrinter.PrintAnimalEaten(prey, predator, prey.getLocation());
                    location.removeAnimal(prey);
                    eaten += prey.getWeight();
                }
            }
        }
        predator.feed(eaten);
    }
}

