package island.model;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCount, int speed, double foodNeeded) {
        super(weight, maxCount, speed, foodNeeded);
    }
}