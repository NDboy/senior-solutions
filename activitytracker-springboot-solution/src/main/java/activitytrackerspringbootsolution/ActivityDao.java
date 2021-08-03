package activitytrackerspringbootsolution;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Repository

public class ActivityDao {

    @PersistenceContext
    private EntityManager manager;

    public ActivityDao(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void saveActivity(Activity activity) {
        manager.persist(activity);
    }


    public Activity findActivityById(Long id) {
        return manager.find(Activity.class, id);
    }


    public List<Activity> listActivities() {
        return manager.createQuery("select a from Activity a order by a.descr", Activity.class).getResultList();
    }

    @Transactional
    public void setDataById(long id, Consumer<Activity> consumer) {
        Activity activity = manager.find(Activity.class, id);
        consumer.accept(activity);
    }

    @Transactional
    public void deleteActivityById(long id) {
        Activity activity = manager.getReference(Activity.class, id);
        manager.remove(activity);
    }


    public Activity findActivityByIdWithLabels(long id) {

        return manager.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void saveTrackPointToActivity(long activityId, TrackPoint trackPoint) {
        Activity activity = manager.find(Activity.class, activityId);

        trackPoint.setActivity(activity);
        manager.persist(trackPoint);

    }


    public Activity findActivityByIdWithTrackPoints(long activityId) {

        return manager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id", Activity.class)
                .setParameter("id", activityId)
                .getSingleResult();

    }


    List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {

        return manager.createNamedQuery("findTrackPointCoordinatesByDate", Coordinate.class)
                .setParameter("startTime", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();

    }


    List<Object[]> findTrackPointCountByActivity(){
        return manager.createNamedQuery("findTrackPointCountByActivity")
                .getResultList();
    }

}
