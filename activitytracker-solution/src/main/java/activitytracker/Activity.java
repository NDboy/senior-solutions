package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "act_desc", nullable = false, length = 200)
    private String descr;

    @Column(name = "act_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    public Activity(LocalDateTime startTime, String descr, ActivityType type) {
        this.startTime = startTime;
        this.descr = descr;
        this.type = type;
    }

    public Activity(String descr) {
        this.descr = descr;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", descr='" + descr + '\'' +
                ", type=" + type +
                '}';
    }

    public static Activity creator(int num) {
        return new Activity(LocalDateTime.of(2020, 10,10,10,10).plusDays(num),
                "This was my " + num + ". activity.",
                ActivityType.RUNNING);
    }
}
