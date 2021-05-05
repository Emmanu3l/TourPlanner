package dataAccessLayer.common;

import models.TourItem;
import models.TourLog;

import java.util.ArrayList;
import java.util.List;

public interface IDatabase {
    int InsertNew(String sqlQuery, ArrayList<Object> parameters); // nach dem einfügen objekt id erhalten
    <T> List<T> TourReader(String sqlQuery); // liest aus datenbank tour items aus (mit method generics für datentyp T)
    <T> List<T> TourReader(String sqlQuery, ArrayList<Object> parameters);
}
