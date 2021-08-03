package activitytrackerspringbootsolution;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
@NamedQuery(name = "findTrackPointCoordinatesByDate", query = "select new activitytrackerspringbootsolution.Coordinate(a.lat, a.lon) " +
                                                            "from Activity a where a.startTime > :startTime")
@NamedQuery(name = "findTrackPointCountByActivity", query = "select a.descr, count(t) from Activity a join a.trackPoints t group by a.descr order by a.descr")
public class Activity {

    @Id
    @GeneratedValue(generator = "act_gen")
    @TableGenerator(name = "act_gen", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "act_desc", nullable = false, length = 200)
    private String descr;

    @Column(name = "act_type",/* nullable = false, */length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    private double lat;

    private double lon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ElementCollection
    private List<String> labels;

//    @OneToOne
//    private TrackPoint trackPoint;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "activity")
    @OrderBy("time")
    private List<TrackPoint> trackPoints;


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

    public Activity(LocalDateTime startTime, String descr, ActivityType type, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> labels) {
        this.startTime = startTime;
        this.descr = descr;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.labels = labels;
    }

    public Activity(LocalDateTime startTime, String descr, double lat, double lon) {
        this.startTime = startTime;
        this.descr = descr;
        this.lat = lat;
        this.lon = lon;
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

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
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

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

//    public TrackPoint getTrackPoint() {
//        return trackPoint;
//    }
//
//    public void setTrackPoint(TrackPoint trackPoint) {
//        this.trackPoint = trackPoint;
//    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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
