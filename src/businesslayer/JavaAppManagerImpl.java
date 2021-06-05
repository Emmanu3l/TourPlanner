package businesslayer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import models.TourItem;
import models.TourLog;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager {

    //TourItemDAO tourItemDAO = new TourItemDAO();

    @Override
    public List<TourItem> GetItems() throws SQLException {
        //return tourItemDAO.GetItems();
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.GetItems();
    }

    //added
    @Override
    public List<TourLog> GetLogs() throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.GetLogs();
    }

    @Override
    public List<TourItem> Search(String itemName, boolean caseSensitive) throws SQLException {
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

    @Override
    public TourItem CreateTourItem(String name, String origin, String destination, String description, double distance) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.AddNewItem(name, origin, destination, description, distance);
    }

    @Override
    public TourLog CreateTourLog(TourItem item, LocalDateTime creationTime, String report, double distance, String totalTime, int rating, String vehicleType, String averageSpeed, int horsepower, int joule, String description) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.AddNewItemLog(item, creationTime, report, distance, totalTime, rating, vehicleType, averageSpeed, horsepower, joule, description);
    }

    @Override
    public void RemoveTourItem(Integer itemId) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        tourItemDAO.RemoveItem(itemId);
    }

    @Override
    public void RemoveLog(Integer id) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        tourLogDAO.RemoveLog(id);
    }

    @Override
    public TourItem EditTourItem(Integer id, TourItem modifiedItem) throws SQLException {
        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();
        return tourItemDAO.EditItem(id, modifiedItem);
    }

    @Override
    public TourLog EditTourLog(TourLog modifiedLog) throws SQLException {
        ITourLogDAO tourLogDAO = DALFactory.CreateTourLogDAO();
        return tourLogDAO.EditLog(modifiedLog);
    }
}
