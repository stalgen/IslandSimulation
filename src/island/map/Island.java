package island.map;

import island.model.*;

import java.util.Arrays;
import java.util.List;

public class Island {
    private final Location[][] map;

    public Island(int width, int height) {
        map = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Location(i, j);
                map[i][j].populate();
            }
        }
    }

    public List<Location> getAllLocations() {
        return Arrays.stream(map).flatMap(Arrays::stream).toList();
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && y >= 0 && x < map.length && y < map[0].length) {
            return map[x][y];
        }
        return null;
    }

    public synchronized void printMap() {
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                Location loc = map[x][y];
                long wolves = loc.getAnimals().stream().filter(a -> a instanceof Wolf).count();
                long bears = loc.getAnimals().stream().filter(a -> a instanceof Bear).count();
                long foxes = loc.getAnimals().stream().filter(a -> a instanceof Fox).count();
                long mouses = loc.getAnimals().stream().filter(a -> a instanceof Mouse).count();
                long rabbits = loc.getAnimals().stream().filter(a -> a instanceof Rabbit).count();
                long ducks = loc.getAnimals().stream().filter(a -> a instanceof Duck).count();
                long caterpillars = loc.getAnimals().stream().filter(a -> a instanceof Caterpillar).count();
                long plants = loc.getPlants().size();

                StringBuilder cell = new StringBuilder("[");
                if (wolves > 0) cell.append("\uD83D\uDC3A").append(wolves);
                if (bears > 0) cell.append("\uD83D\uDC3B").append(bears);
                if (foxes > 0) cell.append("\uD83E\uDD8A").append(foxes);
                if (mouses > 0) cell.append("\uD83D\uDC01").append(mouses);
                if (rabbits > 0) cell.append("\uD83D\uDC07").append(rabbits);
                if (ducks > 0) cell.append("\uD83E\uDD86").append(ducks);
                if (caterpillars > 0) cell.append("\uD83D\uDC1B").append(caterpillars);
                if (plants > 0) cell.append("\uD83C\uDF3F").append(plants);
                cell.append("] ");
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void growAllPlants() {
        for (Location loc : getAllLocations()) {
            loc.growPlants();
        }
    }
}
