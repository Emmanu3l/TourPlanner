package businessLayer;

import models.TourItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaAppManagerImpl implements JavaAppManager {

    @Override
    public List<TourItem> GetItems() {
        TourItem[] tourItems = {
                new TourItem("Item1"),
                new TourItem("Item2"),
                new TourItem("Item3"),
                new TourItem("Item4"),
                new TourItem("Item5")
        };
        return new ArrayList<TourItem>(Arrays.asList(tourItems));
    }

    @Override
    public List<TourItem> Search(String itemName, boolean caseSensitive) {
        return null;
    }
}
