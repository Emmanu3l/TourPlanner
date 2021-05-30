# TourPlanner
This desktop application is developed based on the GUI framework JavaFX. The user creates (bike-, hike-, running- or vacation-) tours in advance and manages the logs and statistical data of accomplished tours.

**Protokoll:**

10.03.2021: Recherche bezüglich der Entwicklung mit Java FX, erstellen des Java FX Projekts, 
implementieren eines simplen Layouts und Buttons (ca. 4 Stunden)

11.03.2021: Experimentieren mit Lambda Expressions und BorderPane (ca. 2 Stunden)

16.03.2021: Erstmaliges experimentieren mit FXML (ca. 3 Stunden)

17.03.2021: Etwas mehr Feinschliff beim fxml file (ca. 2 Stunden)

02.05.2021: Abschließen der Implementierung des Inhalts welcher im Video "SWEI - JavaFxIntro" präsentiert wurde (ca. 4 Stunden)

05.05.2021: Beginnen der Implementierung des Inhalts welcher im Video "JavaFX Intro - Wie implementiert man DAL?" präsentiert wurde (ca. 3 Stunden)

06.05.2021: Fortsetzung der Implementierung (ca. 3 Stunden)

07.05.2021: Fortsetzung der Implementierung (ca. 2 Stunden)

08.05.2021: Fortsetzung der Implementierung (ca. 2 Stunden)

09.05.2021: Fortsetzung der Implementierung (ca. 2 Stunden)

10.05.2021: Probleme mit der DB (ca. 2 Stunden)

11.05.2021: Lösen der Probleme mit der DB, Abschließen der Implementierung des Inhalts der ersten zwei Stunden des DAL Tutorials (ca. 2 Stunden)

12.05.2021: Anpassungen des User Interfaces, Darstellung der Logs (ähnlich wie jene der Items) im User Interface (ca. 4 Stunden)

16.05.2021: Recherche und Arbeit an Deployment. Erfolgreiche Umsetzung von "Package an application into a JAR" und "Run a packaged JAR"
[laut dieser Anleitung](https://www.jetbrains.com/help/idea/compiling-applications.html) (ca 3. Stunden)

17.05.2021 (in der Früh): Buchstäbliche nahtlose Fortsetzung der Arbeit des vorigen Tages. 
Erfolglose Versuche launch4j zu Verwenden, [mithilfe dieses Tutorials](https://youtu.be/jPKxqc8Zg-0) (ca 4. Stunden)

18.05.2021: 
* Erweitern der TourItem und TourLog Klassen bzw. Anpassen der Projektstruktur, welche aus den Tutorials übernommen wurde, an die Problemstellung (ca. 2 Stunden)
* Ermöglichen, dass über die GUI pop-up fenster zum hinzufügen/bearbeiten von Tours/Logs geöffnet werden können (ca. 2 Stunden)

19.05.2021: Hinzufügen von edit window pop-ups und hinzufügen von zusätzlicher Funktionalität bei TourItem.java (ca. 3 Stunden)

20.05.2021: 
* Erweitern von MapQuest.java (ca. 2 Stunden)
* Erweitern von AddTourWindow (ca. 2 Stunden)

21.05.2021: Recherche bzgl. MVVM und erstellung des "viewmodels"-package (ca. 3 Stunden)

22.05.2021: Entscheidung erst mal alles im Controller zu implementieren und es später aufzuteilen, sobald die Basisfunktionalität vorhanden ist.
Fokus darauf eine funktionale Implementierung von addTour zu haben, da die meiste fehlende funktionalität in meinem program eine variation davon ist.
Kleinere Veränderungen an AddTourWindowController.java (3 Stunden)

24.05.2021: Vergebliche Versuche das Einfügen in die Datenbank zum funktionieren zu bringen (ca. 3 Stunden)

27.05.2021: Erweiterung der AddTourWindowController Klasse, beginnen des verschiebens von funktionen vom controller zum view model, implementierung von log4j (ca. 6 Stunden)

28.05.2021: Fehlersuche und Behebung bei der DB, weitere Verbesserungen (ca. 5 Stunden)

29.05.2021: Verbesserungen im MainWindowController, erweitern des TourLogs um mehr Parameter in allen relevanten Klassen, erster erfolgreicher Zugriff auf API, 
weitere kleine Änderungen, Implementierung der Klassen und fxml Files, welche mit der addLog Interaktion zusammenhängen (ca. 6 Stunden)

30.05.2021: Funktion zum löschen von Logs implementiert, weitere Funktionalität in das ViewModel verschoben.

todo: implementierung von viewmodels

TODO+
eigens spezifizierte Tours und Logs hinzugefügen können (im Gegensatz zu den zufällig generierten)
SQL an neue DB Struktur anpassen sodass randomly generated buttons wieder funktionieren
.jar to .exe mittels jlink und launch4j

weiters zu tun:
sobald man die logs und tours vernünftig hinzufügen kann, sollten sie auch detailreicher angezeigt werden (nicht nur eine spalte/eigenschaft)