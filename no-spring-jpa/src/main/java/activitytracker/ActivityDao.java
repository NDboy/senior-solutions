package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(activity);

        transaction.commit();
        manager.close();

    }

    public Activity findActivityById(Long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();

        Activity activity = manager.find(Activity.class, id);

//        transaction.commit();
        manager.close();

        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Activity> activities = manager.createQuery("select a from Activity a", Activity.class).getResultList();

        transaction.commit();
        manager.close();

        return activities;
    }
}
