package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityTrackerMain {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

    public void saveEntities(List<Activity> activities) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        activities.forEach(entityManager::persist);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity( "Biking"));
        activities.add(new Activity(LocalDateTime.of(2020,10,10,13,35,0), "Biking", ActivityType.BIKING));
        activities.add(new Activity(LocalDateTime.of(2020,10,10,13,35,0), "Biking", ActivityType.BIKING));
        activities.add(new Activity(LocalDateTime.now(), "Any other", ActivityType.RUNNING));

        new ActivityTrackerMain().saveEntities(activities);


//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        activities.forEach(entityManager::persist);
//        entityManager.persist(new Activity( "Biking2"));
//
//
//        entityManager.getTransaction().commit();
//
//        entityManager.close();
//        entityManagerFactory.close();

    }

}
