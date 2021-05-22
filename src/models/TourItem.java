package models;

import businesslayer.ConfigurationManager;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;

public class TourItem {

    //every tour consists of name, tour description, route information (an image with the tour map) and tour distance

    @Getter @Setter private Integer Id;
    @Getter @Setter private String Name;
    @Getter @Setter private String Origin;
    @Getter @Setter private String Destination;
    @Getter @Setter private String Description;
    @Getter @Setter private double Distance;

    //TODO: i assume i should set the distance via api request, but i'll keep it like that for now
    public TourItem(Integer id, String name, String description, String origin, String destination, double distance) {
        Id = id;
        Name = name;
        Description = description;
        Origin = origin;
        Destination = destination;
        Distance = distance;
    }

    //maybe this is unnecessary, if you want to get the image there would probably be another method in  another class
    //and you would pass the tour id to get the correct image
    public String getImagePath() throws FileNotFoundException {
        return ConfigurationManager.GetConfigProperty("ImagePath") + "\\" + Id; //TODO add file type at end
    }

}
