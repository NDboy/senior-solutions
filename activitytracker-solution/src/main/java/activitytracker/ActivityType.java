package activitytracker;

import java.util.Random;

public enum ActivityType {

    BIKING, HIKING, RUNNING, BASKETBALL;

    public static ActivityType randomActivityType() {
        Random random = new Random(1);
        return ActivityType.values()[random.nextInt(4)];
    }
}
