package dataaccesslayer.common;

import dataaccesslayer.dao.ITourItemDAO;
import dataaccesslayer.dao.ITourLogDAO;
import dataaccesslayer.postgresSqlServer.Database;
import dataaccesslayer.postgresSqlServer.TourItemPostgresDAO;
import dataaccesslayer.postgresSqlServer.TourLogPostgresDAO;

public class DALFactory {
    //TODO

    private static String packageName;
    private static IDatabase database;


    public static void Init() {
        packageName = ConfigurationManager.GetConfigProperty("DALSqlPackage");
    }

    public static IDatabase GetDatabase() {
        // wenn die database null ist, dann eine neue anlegen
        if (database == null) {
            database = CreateDatabase();
        }
        return database;
    }

    private static IDatabase CreateDatabase() {
        String connectionString = ConfigurationManager.GetConfigProperty("PostgresSqlConnectionString");
        return CreateDatabase(connectionString);
    }

    private static IDatabase CreateDatabase(String connectionString) {
        // Datenbank Objekt über Reflection besorgen
        // überprüfen ob es in unserem package eine klasse mit dem namen gibt
        // dann instanz generieren lassen und zurückgeben
        try {
            Class<Database> cls = (Class<Database>) Class.forName(packageName + "." + Database.class.getName());
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
            Class<TourItemPostgresDAO> cls = (Class<TourItemPostgresDAO>) Class.forName(packageName + "." + TourItemPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourLogDAO CreateTourLogDAO() {
        try {
            Class<TourLogPostgresDAO> cls = (Class<TourLogPostgresDAO>) Class.forName(packageName + "." + TourLogPostgresDAO.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
