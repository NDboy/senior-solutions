package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class TrackPointDao {

    private EntityManagerFactory entityManagerFactory;

    public TrackPointDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveTrackPoint(TrackPoint trackPoint) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(trackPoint);

        transaction.commit();
        manager.close();
    }

//    public void saveActivityAndTrackPoint(TrackPoint trackPoint, Activity activity) {
//        EntityManager manager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//
//        manager.persist(trackPoint);
//        manager.persist(activity);
//        trackPoint.setActivity(activity);
//        activity.setTrackPoint(trackPoint);
//
//        transaction.commit();
//        manager.close();
//    }


    public TrackPoint findTrackPointById(Long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();

        TrackPoint trackPoint = manager.find(TrackPoint.class, id);
        manager.close();

        return trackPoint;
    }


}
