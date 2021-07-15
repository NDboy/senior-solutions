package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

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

        Activity activity = manager.find(Activity.class, id);
        manager.close();

        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager manager = entityManagerFactory.createEntityManager();

        List<Activity> activities = manager.createQuery("select a from Activity a order by a.descr", Activity.class).getResultList();
        manager.close();

        return activities;
    }

    public void setDataById(long id, Consumer<Activity> consumer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        consumer.accept(activity);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteActivityById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
//        Activity activity = em.find(Activity.class, id);
        Activity activity = em.getReference(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Activity activity = manager.createQuery("select a from Activity a join fetch a.labels where id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();

        manager.close();
        return activity;
    }

    public void saveTrackPointToActivity(long activityId, TrackPoint trackPoint) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        Activity activity = manager.find(Activity.class, activityId);

        trackPoint.setActivity(activity);
        manager.persist(trackPoint);
        manager.getTransaction().commit();
        manager.close();

    }

    public Activity findActivityByIdWithTrackPoints(long activityId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Activity foundActivity = manager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id", Activity.class)
                .setParameter("id", activityId)
                .getSingleResult();
        manager.close();
        return foundActivity;
    }



}
