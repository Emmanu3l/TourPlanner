package businesslayer;

import models.TourItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//saving Route information and providing methods for obtaining that information
public class MapQuest {
    //siehe: https://moodle.technikum-wien.at/pluginfile.php/963877/mod_resource/content/1/MapQuestAPI_Kurzanleitung.pdf

    //static map api: https://developer.mapquest.com/documentation/static-map-api/v5/
    //example request: routing
    //https://www.mapquestapi.com/staticmap/v5/map?start=New+York,NY&end=Washington,DC&size=600,400@2x&key=KEY
    public static String createStaticMapImage(TourItem tourItem) throws IOException {
        //TODO: these two variables could be needed by every method in this class. Turn into class variables?
        String resourceURL = "https://www.mapquestapi.com/staticmap/v5/map";
        String key = ConfigurationManager.GetConfigProperty("MapQuestAPIKey");
        //TODO: shouldn't the api call be in the data access layer?

        String requestUrl = resourceURL + "?start=" + tourItem.getOrigin() + "&end=" + tourItem.getDestination() + "&size=600,400@2x" + "&key=" + key;

        URL url = new URL(requestUrl);
        //return ImageIO.read(url);
        BufferedImage image = ImageIO.read(url);
        String imagePath = ConfigurationManager.GetConfigProperty("ImagePath") + tourItem.getId() + ".png";
        ImageIO.write(image, "png", new File(imagePath));
        return imagePath;
    }

    //do i need this though? doesn't it get automatically overwritten because it has the same name? if it doesn't i should just add that aspect (delete file with same name) to the above function
    /*public static String deleteStaticMapImage(TourItem tourItem) {
        String imagePath = ConfigurationManager.GetConfigProperty("ImagePath") + tourItem.getId() + ".png";

    }*/

    //directions api: https://developer.mapquest.com/documentation/directions-api/
    public void getDirections() {
        String resourceURL = "http://www.mapquestapi.com/directions/v2/route";

    }
}
