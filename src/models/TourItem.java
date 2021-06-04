package models;

import businesslayer.MapQuest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class TourItem {

    //every tour consists of name, tour description, route information (an image with the tour map) and tour distance

    @Getter @Setter private Integer Id;
    @Getter @Setter private String Name;
    @Getter @Setter private String Origin;
    @Getter @Setter private String Destination;
    @Getter @Setter private String Description;
    @Getter @Setter private double Distance;

    //TODO: i assume i should set the distance via api request, but i'll keep it like that for now
    @JsonCreator
    public TourItem(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("origin") String origin, @JsonProperty("destination") String destination, @JsonProperty("description") String description, @JsonProperty("distance") double distance) {
        Id = id;
        Name = name;
        Origin = origin;
        Destination = destination;
        Description = description;
        Distance = distance;
    }

    //maybe this is unnecessary, if you want to get the image there would probably be another method in  another class
    //and you would pass the tour id to get the correct image
    /*public String generateImage() throws IOException {
        return MapQuest.createStaticMapImage(this);
    }*/

}
