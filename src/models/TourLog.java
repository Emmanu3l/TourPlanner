package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TourLog {

    //a tour-log consists of date/time, report, distance, total time, and rating taken on the tour

    @Getter @Setter private Integer Id;
    @Getter @Setter private TourItem LogTourItem; // quasi der foreign key, nur dass es keine id sondern ein objekt ist
    @Getter @Setter private LocalDateTime CreationTime;
    @Getter @Setter private String Report;
    @Getter @Setter private double Distance;
    @Getter @Setter private String TotalTime;
    @Getter @Setter private int Rating;

    //additional attributes
    @Getter @Setter private String vehicleType; //fahrrad, auto, motorrad etc
    @Getter @Setter private String averageSpeed;
    @Getter @Setter private int horsepower; //negativ falls kein auto/motorrad
    @Getter @Setter private int joule; //negativ falls kein fahrrad
    @Getter @Setter private String description; //zitate/aussagen bzgl. des trips


    public TourLog(Integer id, TourItem logTourItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating) {
        Id = id;
        LogTourItem = logTourItem;
        CreationTime = creationTime;
        Report = report;
        Distance = distance;
        TotalTime = totalTime;
        Rating = rating;
    }

    public TourLog(Integer id, TourItem logTourItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating, String vehicleType, String averageSpeed, int horsepower, int joule, String description) {
        Id = id;
        LogTourItem = logTourItem;
        CreationTime = creationTime;
        Report = report;
        Distance = distance;
        TotalTime = totalTime;
        Rating = rating;
        this.vehicleType = vehicleType;
        this.averageSpeed = averageSpeed;
        this.horsepower = horsepower;
        this.joule = joule;
        this.description = description;
    }
}
