package dataaccesslayer.postgresSqlServer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import models.TourItem;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class TourItemPostgresDAO implements ITourItemDAO {

    // was man bei einem data access object immer machen muss: sql statements festlegen
    // strichpunkt in sql statement nicht vergessen!
    //TODO: nicht vergessen das zu verändern wenn du die parameter der models anpasst
    private final String SQL_FIND_BY_ID = "SELECT * FROM public.\"TourItems\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_GET_ALL_ITEMS = "SELECT * FROM public.\"TourItems\";";
    private final String SQL_INSERT_NEW_ITEM = "INSERT INTO public.\"TourItems\" (\"Name\",\"Origin\",\"Destination\",\"Description\",\"Distance\") VALUES (?, ?, ?, ?, ?);";
    private final String SQL_REMOVE_ITEM = "DELETE FROM public.\"TourItems\" WHERE \"Id\"=CAST(? AS INTEGER);";
    //private final String SQL_UPDATE_ITEM = "UPDATE public.\"TourItems\" SET (\"Name\"=?,\"Origin\"=?,\"Destination\"=?,\"Description\"=?,\"Distance\"=?) WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_ITEM = "UPDATE public.\"TourItems\" SET \"Name\"=?,\"Origin\"=?,\"Destination\"=?,\"Description\"=?,\"Distance\"=? WHERE \"Id\"=CAST(? AS INTEGER);";

    private IDatabase database;

    public TourItemPostgresDAO() throws FileNotFoundException {
        database = DALFactory.GetDatabase();
    }

    @Override
    public TourItem FindById(Integer itemId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(itemId);

        List<TourItem> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, TourItem.class);

        return tourItems.stream().findFirst().get();
    }

    @Override
    public TourItem AddNewItem(String name, String origin, String destination, String description, double distance) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(origin);
        parameters.add(destination);
        parameters.add(description);
        parameters.add(distance);

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEM, parameters);
        return FindById(resultId);
    }

    @Override
    public List<TourItem> GetItems() throws SQLException {
        return database.TourReader(SQL_GET_ALL_ITEMS, TourItem.class);
    }

    @Override
    public void RemoveItem(int itemId) throws SQLException {
        database.Remove(SQL_REMOVE_ITEM, itemId);
    }

    @Override
    public void EditItem(Integer id, TourItem modifiedItem) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(modifiedItem.getName());
        parameters.add(modifiedItem.getOrigin());
        parameters.add(modifiedItem.getDestination());
        parameters.add(modifiedItem.getDescription());
        parameters.add(modifiedItem.getDistance());
        parameters.add(id);
        database.Update(SQL_UPDATE_ITEM, parameters);
    }
}
