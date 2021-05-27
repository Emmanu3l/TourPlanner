package viewmodels;

import businesslayer.JavaAppManager;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import models.TourItem;

import java.sql.SQLException;
import java.util.List;

public class MainWindowViewModel {

    public void search(ObservableList<TourItem> tourItems, JavaAppManager manager, TextField searchField) throws SQLException {
        tourItems.clear();

        List<TourItem> items = manager.Search(searchField.textProperty().getValue(), false);
        tourItems.addAll(items);
        //search.setText("Results found");
    }

    public void clear(ObservableList<TourItem> tourItems, JavaAppManager manager, TextField searchField) throws SQLException {
        tourItems.clear();
        searchField.textProperty().setValue("");

        List<TourItem> items = manager.GetItems();
        tourItems.addAll(items);
    }
}
