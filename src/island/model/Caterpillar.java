package island.model;

import island.map.Island;
import island.statistics.StatisticsPrinter;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 1000, 0, 0);
    }

    @Override
    public void move(Island island) {
    }

    @Override
    public void eat() {
        if (!location.getPlants().isEmpty()) {
            this.feed(Plant.WEIGHT);
            location.getPlants().remove(0);
        }
    }

    @Override
    public void reproduce() {
        long count = location.getAnimals().stream().filter(a -> a instanceof Caterpillar).count();
        if (count >= 2 && count < maxCount) {
            Animal newAnimal = new Caterpillar();
            location.addAnimal(newAnimal);
            StatisticsPrinter.PrintAnimalBirth(newAnimal, location);
        }
    }
}

