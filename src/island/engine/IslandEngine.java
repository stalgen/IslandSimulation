package island.engine;

import island.map.Island;
import island.map.Location;
import island.model.Animal;
import island.statistics.StatisticsPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;


public class IslandEngine {
    private final Island island;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final ExecutorService animalPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public IslandEngine(Island island) {
        this.island = island;
    }

    public void startSimulation() {
        executorService.scheduleAtFixedRate(this::runCycle, 0, 1, TimeUnit.SECONDS); //to be configured
    }

    private void runCycle() {
        boolean hasAnimals = false;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (Location location : island.getAllLocations()) {
            List<Animal> animalsSnapshot;
            synchronized (location) {
                animalsSnapshot = new ArrayList<>(location.getAnimals());
            }
            if (!animalsSnapshot.isEmpty()) {
                hasAnimals = true;
            }

            tasks.add(() -> {
                List<Animal> shuffledAnimals = new ArrayList<>(animalsSnapshot);
                Collections.shuffle(shuffledAnimals);
                for (Animal animal : shuffledAnimals) {
                    animal.tick();
                    animal.eat();
                    animal.reproduce();
                    animal.move(island);
                }
                return null;
            });
        }

        try {
            List<Future<Void>> futures = animalPool.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        island.growAllPlants();
        island.printMap();
        StatisticsPrinter.print(island);

        if (!hasAnimals) {
            System.out.println("Симуляція завершена: на острові не залишилося тварин.");
            executorService.shutdown();
            animalPool.shutdown();
        }
    }
}
