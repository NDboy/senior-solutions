package activitytrackerspringbootsolution;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class TrackPointDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void saveTrackPoint(TrackPoint trackPoint) {
        manager.persist(trackPoint);
    }


    public TrackPoint findTrackPointById(Long id) {
        return manager.find(TrackPoint.class, id);
    }


}
