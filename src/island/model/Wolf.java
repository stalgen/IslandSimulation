package island.model;

import island.map.Island;
import island.map.Location;
import island.statistics.StatisticsPrinter;
import island.util.FoodProbabilityMap;
import island.util.RandomUtil;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public void move(Island island) {
        Location newLoc = RandomUtil.getRandomLocation(island, location, speed);
        if (newLoc != null && newLoc.getAnimals().size() < maxCount) {
            StatisticsPrinter.PrintAnimalMove(this, location, newLoc);
            location.removeAnimal(this);
            newLoc.addAnimal(this);
        }
    }

    @Override
    public void eat() {
        FoodProbabilityMap.consumeAvailablePrey(this);
    }

    @Override
    public void reproduce() {
        long count = location.getAnimals().stream().filter(a -> a.getClass() == this.getClass()).count();
        if (count >= 2 && count < maxCount) {
            Animal newAnimal = new Wolf();
            location.addAnimal(newAnimal);
            StatisticsPrinter.PrintAnimalBirth(newAnimal, location);
        }
    }
}
