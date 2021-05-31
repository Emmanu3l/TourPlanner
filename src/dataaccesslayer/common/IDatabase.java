package dataaccesslayer.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDatabase {
    int InsertNew(String sqlQuery, ArrayList<Object> parameters) throws SQLException; // nach dem einfügen objekt id erhalten
    <T> List<T> TourReader(String sqlQuery, Class<T> tourType) throws SQLException; // liest aus datenbank tour items aus (mit method generics für datentyp T)
    <T> List<T> TourReader(String sqlQuery, ArrayList<Object> parameters, Class<T> tourType) throws SQLException;

    void Remove(String SQL_REMOVE_ITEM, int itemId) throws SQLException;

    void Update(String sql_update_item, ArrayList<Object> id) throws SQLException;
}
