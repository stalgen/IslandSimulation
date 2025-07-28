package island.util;

import island.map.Island;
import island.map.Location;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public static Location getRandomLocation(Island island, Location current, int speed){
        int x = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int y = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        return island.getLocation(x,y);
    }
}
