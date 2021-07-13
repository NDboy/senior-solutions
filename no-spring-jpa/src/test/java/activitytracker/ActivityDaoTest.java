package activitytracker;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    ActivityDao activityDao;
    List<Activity> activities = new ArrayList<>();
    Activity activitySingle;

    @BeforeEach
    void setUp() {

        MariaDbDataSource dataSource;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker?useUnicode=true");
            dataSource.setUser("activitytracker");
            dataSource.setPassword("activitytracker");

        } catch (SQLException se) {
            throw new IllegalStateException("Can not create data source", se);
        }

        Flyway flyway = Flyway.configure().locations("/db/migration/activitytracker").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);

        IntStream.range(0,10)
                .mapToObj(Activity::creator)
                .forEach(activities::add);

        activitySingle = new Activity(LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING);
    }

    @Test
    void testSaveAndFindActivity() {
        activityDao.saveActivity(activitySingle);
        Long id = activitySingle.getId();
        Activity foundActivity = activityDao.findActivityById(id);

        assertThat(foundActivity)
                .hasToString("Activity{id=1, startTime=2020-10-10T10:10, descr='single activity', type=HIKING}");


        assertThat(List.of(foundActivity))
                .extracting("id", "startTime", "descr", "type")
                .contains(tuple(1L, LocalDateTime.of(2020,10, 10, 10, 10), "single activity", ActivityType.HIKING));

    }


    @Test
    void testListActivities() {
        activities.forEach(activityDao::saveActivity);
        List<Activity> foundActivities = activityDao.listActivities();

        assertThat(activities)
                .hasSize(10)
                .extracting("id", "startTime", "descr", "type")
                .contains(tuple(1L, LocalDateTime.of(2020,10, 10, 10, 10), "This was my 0. activity.", ActivityType.RUNNING),
                        tuple(4L, LocalDateTime.of(2020,10, 13, 10, 10), "This was my 3. activity.", ActivityType.RUNNING),
                        tuple(8L, LocalDateTime.of(2020,10, 17, 10, 10), "This was my 7. activity.", ActivityType.RUNNING));


    }

    @Test
    void testSetDataById() {
        activityDao.saveActivity(activitySingle);
        Long id = activitySingle.getId();
        activityDao.setDataById(id, a -> a.setDescr("!!!fake activity!!!"));
        activityDao.setDataById(id, a -> a.setType(ActivityType.BASKETBALL));

        assertThat(activityDao.findActivityById(id))
                .hasToString("Activity{id=1, startTime=2020-10-10T10:10, descr='!!!fake activity!!!', type=BASKETBALL}");

    }

    @Test
    void testDeleteActivityById() {
        activities.forEach(activityDao::saveActivity);
        activityDao.deleteActivityById(1);

        List<Activity> leftActivities = activityDao.listActivities();
        assertThat(leftActivities)
                .hasSize(9)
                .extracting("id")
                .contains(2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)
                .doesNotContain(1L);
    }
}