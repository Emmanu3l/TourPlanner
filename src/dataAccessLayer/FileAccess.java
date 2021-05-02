package dataAccessLayer;

import models.TourItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccess implements DataAccess {

    private String filePath;

    public FileAccess() {
        // get info from config file
        filePath = "...";

    }

    @Override
    public List<TourItem> GetItems() {
        // read tour items from file systems
        TourItem[] tourItems = {
                new TourItem("Item1"),
                new TourItem("Item2"),
                new TourItem("Item3"),
                new TourItem("Item4"),
                new TourItem("Item5")
        };
        return new ArrayList<TourItem>(Arrays.asList(tourItems));
    }
}
