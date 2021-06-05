package businesslayer;

import models.TourItem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestTest {

    @Test
    void createStaticMapImage() throws IOException {
        //check whether the image is generated
        TourItem tourItem = new TourItem(1, "name", "origin", "destination", "description", 1.0);
        String imagePath = MapQuest.createStaticMapImage(tourItem);
        File file = new File(imagePath);
        assert(file.exists());
    }
}