package businesslayer;

import models.TourItem;

import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager {

    //TourItemDAO tourItemDAO = new TourItemDAO();

    @Override
    public List<TourItem> GetItems() {
        //return tourItemDAO.GetItems();
        return null;
    }

    @Override
    public List<TourItem> Search(String itemName, boolean caseSensitive) {
        List<TourItem> items = GetItems();

        if(caseSensitive) {
            return items
                    .stream()
                    .filter(x -> x.getName().contains(itemName))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(itemName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
