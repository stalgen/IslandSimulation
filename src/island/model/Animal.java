package island.model;

import island.map.Island;
import island.map.Location;
import island.statistics.StatisticsPrinter;


public abstract class Animal {
    protected double weight;
    protected int maxCount;
    protected int speed;
    protected double foodNeeded;
    protected double currentHunger;
    protected Location location;

    public Animal(double weight, int maxCount, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.currentHunger = 0;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public abstract void move(Island island);

    public abstract void eat();

    public abstract void reproduce();

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public double getRemainingHunger() {
        return foodNeeded - (foodNeeded - currentHunger);
    }

    public void feed(double amountEaten) {
        currentHunger = Math.max(0, currentHunger - amountEaten);
    }

    public void tickHunger() {
        currentHunger += foodNeeded * 0.1;
        if (currentHunger >= foodNeeded) {
            die();
        }
    }

    public void die() {
        if (location != null) {
            StatisticsPrinter.PrintAnimalDeath(this, this.getLocation());
            location.removeAnimal(this);
        }
    }

    public void tick() {
        tickHunger();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

