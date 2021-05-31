package dataaccesslayer.postgresSqlServer;

import businesslayer.ConfigurationManager;
import dataaccesslayer.common.DALFactory;
import dataaccesslayer.common.IDatabase;
import dataaccesslayer.dao.ITourItemDAO;
import models.TourItem;
import models.TourLog;

import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database implements IDatabase {

    private String connectionString; // verbindung zur datenbank aufbauen

    public Database(String connectionString) {
        this.connectionString = connectionString;
    }

    private Connection CreateConnection() throws SQLException {
        // try catch block um die exception zu fangen falls die connection nicht funktioniert
        try {
            // DriverManager ermöglicht mit dem connection string & zugangsdaten auf eine sql db zuzugreifen
            // generell sollte man diese zugangsdaten nicht im code sondern in einem config file haben
            return DriverManager.getConnection(connectionString, ConfigurationManager.GetConfigProperty("DatabaseUser"), ConfigurationManager.GetConfigProperty("DatabasePassword"));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace(); // besser als System.out.println()
        }
        throw new SQLException("Establishing database connection failed.");
    }

    @Override
    public int InsertNew(String sqlQuery, ArrayList<Object> parameters) throws SQLException {
        // connection wird im try block angelegt
        // sobald dieser verlassen wird, wird die connection automatisch geschlossen

        // prepared statement weil parameter mitgegeben wurden
        // Statement.RETURN_GENERATED_KEYS -> id der angelegten datenelemente werden zurückgegeben

        // beide statements im kopf des try statements damit die connection und das prepared statement
        // automatisch geschlossen werden sobald der scope verlassen wird
        try (Connection connection = CreateConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            // dynamically add parameters
            for (int i = 0; i < parameters.size(); i++) {
                // parameter sind als string hinterlegt; string index fängt bei 1 an
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            // nachdem die parameter gesetzt wurden können die prepared statements ausgeführt werden
            // wieviele reihen hat das insert statement betroffen -> affectedRows (im besten fall 1)
            int affectedRows = preparedStatement.executeUpdate(); // wird für insert, update, delete verwendet

            // wenn etwas eingefügt wurde
            if (affectedRows > 0) {
                // wieder in try weil ansonsten auch für das ResultSet immer wieder close() aufgerufen werden müsste
                // try garantiert, dass das automatisch passiert
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // gibt es in generated keys result set einen eintrag? dann:
                        return generatedKeys.getInt(1); // column index fängt bei 1 an
                    }
                }
            }

        } catch (SQLException e) { //das catch and throw statement versteckt womöglich eigentliche hilfreiche fehlermeldungen
            e.printStackTrace();
        }
        throw new SQLException("Creating data failed, no ID obtained. " + sqlQuery);
    }

    @Override
    public <T> List<T> TourReader(String sqlQuery, Class<T> tourType) throws SQLException {
        try (Connection connection = CreateConnection();
             // normales select statement statt prepared statement
             // statement ausführen und result set auslesen
             // beim prepared statement war die sql query schon am anfang dabei
             // hier muss der leere body nachträglich befüllt werden
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery(sqlQuery);
            // entweder TourItems oder TourLogs werden zurückgegegeben
            if (tourType.getTypeName().equals(TourItem.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if (tourType.getTypeName().equals(TourLog.class.getName())) {
                return (List<T>) QueryTourLogDataFromResultSet(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Reading data failed. " + sqlQuery);
    }

    @Override
    public <T> List<T> TourReader(String sqlQuery, ArrayList<Object> parameters, Class<T> tourType) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            // dynamically add parameters
            for (int i = 0; i < parameters.size(); i++) {
                // parameter sind als string hinterlegt; string index fängt bei 1 an
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }

            ResultSet result = preparedStatement.executeQuery();
            if (tourType.getTypeName().equals(TourItem.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if (tourType.getTypeName().equals(TourLog.class.getName())) {
                return (List<T>) QueryTourLogDataFromResultSet(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Creating data failed, no ID obtained. " + sqlQuery);
    }

    @Override
    public void Remove(String SQL_REMOVE_ITEM, int itemId) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_ITEM)) {
            preparedStatement.setInt(1, itemId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(String sql_update_item, ArrayList<Object> parameters) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql_update_item)) {

            // dynamically add parameters
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            preparedStatement.executeUpdate(); // wird für insert, update, delete verwendet

        } catch (SQLException e) { //das catch and throw statement versteckt womöglich eigentliche hilfreiche fehlermeldungen
            e.printStackTrace();
        }
        //throw new SQLException("Updating data failed, no ID obtained. " + sql_update_item);
    }

    private List<TourItem> QueryTourItemDataFromResultSet(ResultSet result) throws SQLException {
        List<TourItem> tourItemList = new ArrayList<TourItem>();

        //immer wenn noch ein eintrag vorhanden ist:
        // zuerst id nehmen, dann namen etc.
        while(result.next()) {
            tourItemList.add(new TourItem(
                    result.getInt("Id"),
                    result.getString("Name"),
                    result.getString("Origin"),
                    result.getString("Destination"),
                    result.getString("Description"),
                    result.getDouble("Distance")
            ));
        }

        return tourItemList;
    }

    private List<TourLog> QueryTourLogDataFromResultSet(ResultSet result) throws SQLException {
        List<TourLog> tourLogList = new ArrayList<TourLog>();
        //damit man die local date time der tour items richtig verwenden kann:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        ITourItemDAO tourItemDAO = DALFactory.CreateTourItemDAO();

        while(result.next()) {
            tourLogList.add(new TourLog(
                    result.getInt("Id"),
                    tourItemDAO.FindById(result.getInt("TourItemId")),
                    LocalDateTime.parse(result.getString("CreationTime"), formatter),
                    result.getString("Report"),
                    result.getDouble("Distance"),
                    result.getString("TotalTime"),
                    result.getInt("Rating"),
                    result.getString("VehicleType"),
                    result.getString("AverageSpeed"),
                    result.getInt("Horsepower"),
                    result.getInt("Joule"),
                    result.getString("Description")
            ));
        }

        return tourLogList;
    }

}
