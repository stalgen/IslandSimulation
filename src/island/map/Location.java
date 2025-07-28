package island.map;

import island.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Location {
    private final int x, y;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void growPlants() {
        plants.add(new Plant());
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
        //return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void populate() {
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 4); i++) {
            addAnimal(new Wolf());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 3); i++) {
            addAnimal(new Fox());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 2); i++) {
            addAnimal(new Bear());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 10); i++) {
            addAnimal(new Rabbit());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 5); i++) {
            addAnimal(new Mouse());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 5); i++) {
            addAnimal(new Duck());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 20); i++) {
            addAnimal(new Caterpillar());
        }
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 10); i++) {
            growPlants();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x + ":" + y + ']';
    }
}

