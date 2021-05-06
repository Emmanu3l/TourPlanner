package dataaccesslayer.postgresSqlServer;

import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import models.TourItem;

import java.time.LocalDateTime;
import java.util.List;

public class TourItemPostgresDAO implements ITourItemDAO {

    // was man bei einem data access object immer machen muss: sql statements festlegen
    // strichpunkt in sql statement nicht vergessen!
    private final String SQL_FIND_BY_ID = "SELECT * FROM public.\"TourItems\" WHERE \"ID\"=CAST(? AS INTEGER);";
    private final String SQL_GET_ALL_ITEMS = "SELECT * FROM public.\"TourItems\";";
    private final String SQL_INSERT_NEW_ITEM = "INSERT INTO public.\"TourItems\" (\"Name\",\"Url\",\"CreationDate\") VALUES (?, ?, ?);";

    private IDatabase database;

    public TourItemPostgresDAO() {
        //database = DALFactory.CreateDatabase();
    }

    public TourItemPostgresDAO(IDatabase database) {
        this.database = database;
    }

    @Override
    public TourItem FindById(Integer itemId) {
        //TODO hier fortsetzen
        return null;
    }

    @Override
    public TourItem AddNewItem(String name, String url, LocalDateTime creationTime) {
        return null;
    }

    @Override
    public List<TourItem> GetItems() {
        return null;
    }
}
