package island.model;
import island.map.Island;
import island.map.Location;
import island.statistics.StatisticsPrinter;
import island.util.RandomUtil;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 500, 1, 0.01);
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
        if (!location.getPlants().isEmpty()) {
            this.feed(Plant.WEIGHT);
            location.getPlants().remove(0);
        }
    }

    @Override
    public void reproduce() {
        long count = location.getAnimals().stream().filter(a -> a instanceof Mouse).count();
        if (count >= 2 && count < maxCount) {
            Animal newAnimal = new Mouse();
            location.addAnimal(newAnimal);
            StatisticsPrinter.PrintAnimalBirth(newAnimal, location);
        }
    }
}
