package dataAccessLayer;

import models.TourItem;

import java.util.List;

public class TourItemDAO {

    private DataAccess dataAccess;

    public TourItemDAO() {
        // hier könnte man mit einem if oder switch bestimmen welcher weg gewählt werden soll
        //dataAccess = new Database();
        dataAccess = new FileAccess();
    }

    public List<TourItem> GetItems() {
        return dataAccess.GetItems();
    }

}
