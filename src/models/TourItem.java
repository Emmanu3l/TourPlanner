package models;

import lombok.Getter;
import lombok.Setter;

public class TourItem {

    //every tour consists of name, tour description, route information (an image with the tour map) and tour distance

    @Getter @Setter public Integer Id;
    @Getter @Setter public String Name;
    @Getter @Setter public String Description;
    @Getter @Setter public String RouteInformation;
    @Getter @Setter public String ImagePath;
    @Getter @Setter public double Distance;

    public TourItem(Integer id, String name, String description, String routeInformation, String imagePath, double distance) {
        Id = id;
        Name = name;
        Description = description;
        RouteInformation = routeInformation;
        ImagePath = imagePath;
        Distance = distance;
    }
}
