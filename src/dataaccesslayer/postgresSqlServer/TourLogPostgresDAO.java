package dataaccesslayer.postgresSqlServer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import models.TourItem;
import models.TourLog;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TourLogPostgresDAO implements ITourLogDAO {

    //TODO: nicht vergessen das zu verändern wenn du die parameter der models anpasst
    private final String SQL_FIND_BY_ID = "SELECT * FROM public.\"TourLogs\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_FIND_BY_TOURITEM = "SELECT * FROM public.\"TourLogs\" WHERE \"TourItemId\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_NEW_ITEMLOG = "INSERT INTO public.\"TourLogs\" (\"LogText\", \"TourItemId\") VALUES (?, CAST(? AS INTEGER));";
    private final String SQL_GET_ALL_LOGS = "SELECT * FROM public.\"TourLogs\";"; //added

    private IDatabase database;
    private ITourItemDAO tourItemDAO;

    public TourLogPostgresDAO() throws FileNotFoundException {
        this.database = DALFactory.GetDatabase();
        this.tourItemDAO = DALFactory.CreateTourItemDAO();
    }

    @Override
    public TourLog FindById(Integer logId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logId);

        List<TourLog> tourLogs = database.TourReader(SQL_FIND_BY_ID, parameters, TourLog.class);
        return tourLogs.stream().findFirst().get();
    }

    @Override
    public TourLog AddNewItemLog(String logText, TourItem logItem) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logText);
        parameters.add(logItem.getId());
        //parameters.add(creationTime.format(formatter));
        //TODO: sobald ich addnewitemlog auf die neuen parameter angepasst habe wird eine variable creationTime dabei sein
        // das wird sich bemerkbar machen wenn ich die create log related funktionen im appmanager, appmanagerimpl, itourlogdao und tourlogpostgresdao anpassen will

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEMLOG, parameters);
        return FindById(resultId);
    }

    @Override
    public List<TourLog> GetLogsForItem(TourItem item) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(item.getId());

        return database.TourReader(SQL_FIND_BY_TOURITEM, parameters, TourLog.class);
    }

    //added
    @Override
    public List<TourLog> GetLogs() throws SQLException {
        return database.TourReader(SQL_GET_ALL_LOGS, TourLog.class);
    }
}
