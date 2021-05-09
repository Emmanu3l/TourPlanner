package dataaccesslayer.common;

import businesslayer.ConfigurationManager;
import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import dataaccesslayer.postgresSqlServer.Database;
import dataaccesslayer.postgresSqlServer.TourItemPostgresDAO;
import dataaccesslayer.postgresSqlServer.TourLogPostgresDAO;

import java.io.FileNotFoundException;

public class DALFactory {

    private static IDatabase database;

    public static IDatabase GetDatabase() throws FileNotFoundException {
        // wenn die database null ist, dann eine neue anlegen
        if (database == null) {
            database = CreateDatabase();
        }
        return database;
    }

    private static IDatabase CreateDatabase() throws FileNotFoundException {
        String connectionString = ConfigurationManager.GetConfigProperty("PostgresSqlConnectionString");
        return CreateDatabase(connectionString);
    }

    private static IDatabase CreateDatabase(String connectionString) {
        // Datenbank Objekt über Reflection besorgen
        // überprüfen ob es in unserem package eine klasse mit dem namen gibt
        // dann instanz generieren lassen und zurückgeben
        try {
            Class<Database> cls = (Class<Database>) Class.forName(Database.class.getName());
            // konstruktor mit dem parameter string, mit neuere instanz aufrufen, welche den connectionString erhält
            return cls.getConstructor(String.class).newInstance(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // für touritem und tourlogs daos automatisch per reflection generieren lassen
    public static ITourItemDAO CreateTourItemDAO() {
        try {
            Class<TourItemPostgresDAO> cls = (Class<TourItemPostgresDAO>) Class.forName(TourItemPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourLogDAO CreateTourLogDAO() {
        try {
            Class<TourLogPostgresDAO> cls = (Class<TourLogPostgresDAO>) Class.forName(TourLogPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
