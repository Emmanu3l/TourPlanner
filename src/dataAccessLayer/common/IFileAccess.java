package dataAccessLayer.common;

import models.TourItem;
import models.TourTypes;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public interface IFileAccess {
    // neue tour items und tour logs erstellen
    int CreateTourItemFile(String name, String url, LocalDateTime creationTime); // int damit man die id von item erhält
    int CreateTourLogFile(String logText, TourItem tourItem);
    // Suchen bzw. alle dateien zurück geben. File Klasse hat Dateiinfo über die gewählten files.
    List<File> SearchFiles(String searchTerm, TourTypes searchType); // neue klasse TourTypes um zu spezifizieren nach welchem datentyp man sucht
    List<File> GetAllFiles(TourTypes tourType);
}
