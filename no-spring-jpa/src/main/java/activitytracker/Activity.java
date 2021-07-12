package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime startTime;

    @Column(name = "act_desc")
    private String descr;

    @Column(name = "act_type")
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

    public void setDesc(String descr) {
        this.descr = descr;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
