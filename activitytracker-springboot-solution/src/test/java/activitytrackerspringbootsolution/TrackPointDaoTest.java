package activitytrackerspringbootsolution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
class TrackPointDaoTest {

    @Autowired
    private TrackPointDao trackPointDao;

    @Autowired
    private ActivityDao activityDao;

    @BeforeEach
    void setUp() {

    }

//    @Test
//    void testSaveAndFindActivityPoint() {
//        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,1),47.0214, 35.0872);
//        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2021,7,2),47.9854, 35.1234);
//        trackPointDao.saveTrackPoint(trackPoint0);
//        trackPointDao.saveTrackPoint(trackPoint1);
//
//        TrackPoint foundTrackPoint0 = trackPointDao.findTrackPointById(trackPoint0.getId());
//        TrackPoint foundTrackPoint1 = trackPointDao.findTrackPointById(trackPoint1.getId());
//
//        assertThat(foundTrackPoint0)
//                .hasToString("TrackPoint{id=1, time=2021-07-01, latitude=47.0214, longitude=35.0872, activity=null}");
//
//        assertThat(foundTrackPoint1)
//                .hasToString("TrackPoint{id=2, time=2021-07-02, latitude=47.9854, longitude=35.1234, activity=null}");
//
//    }

//    @Test
//    void testSaveActivityWithTrackPoint() {
//        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,1),47.0214, 35.0872);
//        Activity activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
//        trackPointDao.saveTrackPoint(trackPoint0);
////        trackPoint0.setActivity(activitySingle);
////        activitySingle.setTrackPoint(trackPoint0);
//        activityDao.saveActivity(activitySingle);
////        trackPointDao.saveActivityPoint(trackPoint0);
////        activityDao.saveActivity(activitySingle);
//    }

//    @Test
//    void testSaveActivityWithTrackPoint2() {
//        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,1),47.0214, 35.0872);
//        Activity activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
//        trackPointDao.saveActivityAndTrackPoint(trackPoint0, activitySingle);
////        trackPoint0.setActivity(activitySingle);
////        activitySingle.setTrackPoint(trackPoint0);
////        activityDao.saveActivity(activitySingle);
////        trackPointDao.saveTrackPoint(trackPoint0);
////        activityDao.saveActivity(activitySingle);
//    }

    @Test
    void testSaveActivityWithTrackPoints() {
        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,1),47.0214, 35.0872);
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2021,7,2),47.9854, 35.1234);

        Activity activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
        activitySingle.addTrackPoint(trackPoint0);
        activitySingle.addTrackPoint(trackPoint1);

        activityDao.saveActivity(activitySingle);

        assertThat(activityDao.findActivityByIdWithTrackPoints(activitySingle.getId()).getTrackPoints())
                .hasSize(2)
                .extracting("latitude", "longitude")
                .contains(tuple(47.0214, 35.0872), tuple(47.9854, 35.1234));
    }

    @Test
    void testAddTrackPoint() {
        Activity activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
        activityDao.saveActivity(activitySingle);

        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,1),47.0214, 35.0872);
        activityDao.saveTrackPointToActivity(activitySingle.getId(), trackPoint0);

        assertThat(activityDao.findActivityByIdWithTrackPoints(activitySingle.getId()).getTrackPoints())
            .hasSize(1)
            .extracting("latitude", "longitude")
            .contains(tuple(47.0214, 35.0872));
    }

    @Test
    void testFindActivityWithOrderedTrackPointsByTime() {
        Activity activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
        activityDao.saveActivity(activitySingle);

        TrackPoint trackPoint0 = new TrackPoint(LocalDate.of(2021,7,9),47.0214, 35.0872);
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2021,7,1),47.0223, 35.0512);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2021,7,5),47.0301, 35.0654);
        TrackPoint trackPoint3 = new TrackPoint(LocalDate.of(2021,7,2),47.0127, 35.0010);
        activityDao.saveTrackPointToActivity(activitySingle.getId(), trackPoint0);
        activityDao.saveTrackPointToActivity(activitySingle.getId(), trackPoint1);
        activityDao.saveTrackPointToActivity(activitySingle.getId(), trackPoint2);
        activityDao.saveTrackPointToActivity(activitySingle.getId(), trackPoint3);

        assertThat(activityDao.findActivityByIdWithTrackPoints(activitySingle.getId()).getTrackPoints())
                .hasSize(4)
                .extracting("time", "latitude", "longitude")
                .hasToString("[(2021-07-01 (java.time.LocalDate), 47.0223, 35.0512), " +
                            "(2021-07-02 (java.time.LocalDate), 47.0127, 35.001), " +
                            "(2021-07-05 (java.time.LocalDate), 47.0301, 35.0654), " +
                            "(2021-07-09 (java.time.LocalDate), 47.0214, 35.0872)]");

    }

}