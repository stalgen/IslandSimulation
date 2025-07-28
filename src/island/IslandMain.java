package island;

import island.engine.IslandEngine;
import island.map.Island;


public class IslandMain {
    public static void main(String[] args) {
        new IslandEngine(new Island(10, 10)).startSimulation();
    }
}
