package models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @Getter @Setter private String VehicleType; //fahrrad, auto, motorrad etc
    @Getter @Setter private String AverageSpeed;
    @Getter @Setter private int Horsepower; //negativ falls kein auto/motorrad
    @Getter @Setter private int Joule; //negativ falls kein fahrrad
    @Getter @Setter private String Description; //zitate/aussagen bzgl. des trips


    /*public TourLog(Integer id, TourItem logTourItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating) {
        Id = id;
        LogTourItem = logTourItem;
        CreationTime = creationTime;
        Report = report;
        Distance = distance;
        TotalTime = totalTime;
        Rating = rating;
    }*/

    public TourLog(Integer id, TourItem logTourItem, LocalDateTime creationTime, String report, double distance, String totalTime, int rating, String vehicleType, String averageSpeed, int horsepower, int joule, String description) {
        Id = id;
        LogTourItem = logTourItem;
        CreationTime = creationTime;
        Report = report;
        Distance = distance;
        TotalTime = totalTime;
        Rating = rating;
        VehicleType = vehicleType;
        AverageSpeed = averageSpeed;
        Horsepower = horsepower;
        Joule = joule;
        Description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourLog tourLog = (TourLog) o;
        return Double.compare(tourLog.Distance, Distance) == 0 && Rating == tourLog.Rating && Horsepower == tourLog.Horsepower && Joule == tourLog.Joule && Objects.equals(Id, tourLog.Id) && Objects.equals(LogTourItem, tourLog.LogTourItem) && Objects.equals(CreationTime, tourLog.CreationTime) && Objects.equals(Report, tourLog.Report) && Objects.equals(TotalTime, tourLog.TotalTime) && Objects.equals(VehicleType, tourLog.VehicleType) && Objects.equals(AverageSpeed, tourLog.AverageSpeed) && Objects.equals(Description, tourLog.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, LogTourItem, CreationTime, Report, Distance, TotalTime, Rating, VehicleType, AverageSpeed, Horsepower, Joule, Description);
    }

    @Override
    public String toString() {
        return "TourLog{" +
                "Id=" + Id +
                ", LogTourItem=" + LogTourItem +
                ", CreationTime=" + CreationTime +
                ", Report='" + Report + '\'' +
                ", Distance=" + Distance +
                ", TotalTime='" + TotalTime + '\'' +
                ", Rating=" + Rating +
                ", VehicleType='" + VehicleType + '\'' +
                ", AverageSpeed='" + AverageSpeed + '\'' +
                ", Horsepower=" + Horsepower +
                ", Joule=" + Joule +
                ", Description='" + Description + '\'' +
                '}';
    }


}
