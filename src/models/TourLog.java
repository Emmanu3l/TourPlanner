package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TourLog {

    //a tour-log consists of date/time, report, distance, total time, and rating taken on the tour

    @Getter @Setter public Integer Id;
    @Getter @Setter public TourItem LogTourItem; // quasi der foreign key, nur dass es keine id sondern ein objekt ist
    @Getter @Setter public LocalDateTime CreationTime;
    @Getter @Setter public String Report;
    @Getter @Setter public double Distance;
    @Getter @Setter public String TotalTime;
    @Getter @Setter public int Rating;


    public TourLog(Integer id, TourItem logTourItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating) {
        Id = id;
        LogTourItem = logTourItem;
        CreationTime = creationTime;
        Report = report;
        Distance = distance;
        TotalTime = totalTime;
        Rating = rating;
    }


}
