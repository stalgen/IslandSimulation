package island.model;

import island.map.Island;
import island.map.Location;
import island.statistics.StatisticsPrinter;
import island.util.RandomUtil;
import java.util.Iterator;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 200, 4, 0.15);
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
        if (!location.getPlants().isEmpty() && location.getAnimals().stream().filter(a -> a instanceof Caterpillar).count() == 0) {
            location.getPlants().remove(0);
        } else {
            Iterator<Animal> it = location.getAnimals().iterator();
            while (it.hasNext()) {
                Animal a = it.next();
                if (a instanceof Caterpillar) {
                    this.feed(a.getWeight());
                    it.remove();
                    break;
                }
            }
        }
    }

    @Override
    public void reproduce() {
        long count = location.getAnimals().stream().filter(a -> a instanceof Duck).count();
        if (count >= 2 && count < maxCount) {
            Animal newAnimal = new Duck();
            location.addAnimal(newAnimal);
            StatisticsPrinter.PrintAnimalBirth(newAnimal, location);
        }
    }
}
