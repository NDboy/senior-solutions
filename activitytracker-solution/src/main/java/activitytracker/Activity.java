package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(generator = "act_gen")
    @TableGenerator(name = "act_gen", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "act_desc", nullable = false, length = 200)
    private String descr;

    @Column(name = "act_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Activity(LocalDateTime startTime, String descr, ActivityType type) {
        this.startTime = startTime;
        this.descr = descr;
        this.type = type;
    }

    public Activity(LocalDateTime startTime, String descr, ActivityType type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.startTime = startTime;
        this.descr = descr;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Activity(String descr) {
        this.descr = descr;
    }

    public Activity() {
    }

    @PrePersist
    public void initCreationTime() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void initUpdateTime() {
        updatedAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
