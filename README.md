# TourPlanner
This desktop application is developed based on the GUI framework JavaFX. The user creates (bike-, hike-, running- or vacation-) tours in advance and manages the logs and statistical data of accomplished tours.

**Protokoll (inkl. investierte Zeit):**

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

30.05.2021: Funktion zum löschen von Logs implementiert, weitere Funktionalität in das ViewModel verschoben, import von iText pdf und erste rudimentäre Implementierung, 
erstellen und implementieren der copyTour und copyLog Funktionen (ca. 4 Stunden)

31.05.2021: Implementieren von modify tour (fertig) and modify log (wip). Hinzufügen eines view map buttons. Korrigieren der SQL statements. (ca. 5 Stunden)

01.06.2021: Korrigieren des update tour sql statements. Rudimentäre pdf Generierung. Beibehalten der existierenden Werte beim bearbeiten einer Tour. 
Mehr MVVM Aspekte. Fortsetzung der Implementierung von modify log. (ca. 3 Stunden)

02.06.2021: Weiter daran arbeiten, dass MVVM eingehalten wird (ca. 2 Stunden)

03.06.2021: MVVM Aspekte bei AddTour, EditTour, AddLog und EditLog, polishing von anderen Aspekten bei editTour und editLog (alte Werte werden automatisch in die Textfelder eingefügt). Implementieren aller ViewModels. Anschließend die Erkenntis, dass 
man für logs und tours nur jeweils ein viewmodel braucht -> korrigieren. (ca. 5 Stunden)

04.06.2021: Modifizieren der Tabellen sodass cascade delete funktioniert (TODO: assoziierte logs aus dem gui löschen ohne neustart -> refresh all? oder suchen und löschen?). 
Implementieren von Import und Export. (ca. 4 Stunden)

05.06.2021: Implementieren von 20 Unit-Tests (ca. 5 Stunden)

06.06.2021: Verfassen der Absätze, die in der Checkliste verlangt werden (ca. 2 Stunden)

reports: initial state, added log/tour, end state. also: specific tour report button. all called from vm. tour reports folder?

*App Architektur*
Layer, Layer Inhalte/Funktionalität
Die vorhandenen Layer sind der Business-Layer (BL), der Data-Access-Layer (DAL), der Model-Layer, der View-Layer und 
zu guter Letzt der View-Model Layer (VM).
Der BL enthält Klassen wie MapQuest, PDFGenerator, und Import/Export. Genauer gesagt, Klassen die von speziellen Libraries 
oder APIs gebrauch machen.
Der DAL hingegen enthält hauptsächlich Klassen die mit der DB-Connection zusammenhängen. 
Der Model-Layer enthält die Klassen für die TourItems und TourLogs.
Im View-Layer sind die Controller und .fxml Files für die Verschiedenen Fenster: zuerst die Main Window, dann 
die spezifischen Interaktionen: Add/Edit Tour/Log.
Im VM sind die Methoden, die vom User über den View aufgerufen werden. 
Das sind beispielsweise Features die Methoden die im BL oder DAL enthalten sind nutzen.

*Architektur, UX, und Library Entscheidungen*
Was UX betrifft habe ich mich vorrangig dafür entschieden mich an dem Beispielbild in der Aufgabenstellung zu orientieren. 
Was zusätzliche Funktionalität betrifft habe ich versucht das Interface simpel zu halten und Features die zusammen gehören zu gruppieren.
Was Libraries betrifft habe ich mich für Jackson entschieden, da ich es auch schon beim Projekt in SWE1 für JSON verwendet habe.
Log4j und iTextPDF habe ich gewählt, weil sie in der LVA empfohlen/vorgeschlagen wurden. 
Zudem habe ich nach ein wenig Recherche festegestellt, dass sie bezüglich Java gewissermaßen der de facto Standard in ihren jeweiligen Kategorien sind.
Zu Architektur Entscheidungen kann ich nur sagen, dass ich versucht habe MVVM in der vorhandenen Zeit bestmöglich umzusetzen.

*Beschreibung des Implementierten Design Patterns*
Das verwendete Design Pattern ist das 'Factory method design pattern'. Es ist praktisch, da es zu einer losen Kopplung führt. 
Dadurch sind die Änderungen von einzelnen Klassen, wie es oft bei der Erweiterung oder Modifizierung der Attribute 
der TourItem und TourLog Klassen vonnöten war, mit weniger Aufwand verbunden.

*Unit Test Entscheidungen*
Import und Export sind ein wichtiges Feature wo jedoch leicht etwas schiefgehen kann, weshalb ich mich dafür entschieden habe es zu testen. 
Gleiches gilt für MapQuest und den PDFGenerator. Alle drei Klassen haben gemeinsam, dass ihre Methoden static und isoliert von anderen sind, 
weshalb sie zusätzlich ohne Komplikationen testbar sind.
JavaAppManagerImpl wurde gewählt, da die Klasse auf die Funktionalitäten von diversen Interfaces und Klassen erbt bzw. darauf zugreift. 
Somit ist es praktisch um indirekt alle diese Klassen zu testen.
Der ConfigurationManager ist zwar simpel, jedoch würde eine Exception hier gravierend sein, weshalb ich auch diesen teste.
Zu guter letzt habe ich die TourItem und TourLog Klassen getestet um sicherzustellen, dass hier keine Probleme auftreten, 
da die Objekte immerhin ausgiebig in den anderen Testklassen verwendet werden (bspw. in Kombination mit dem JavaAppManager oder assertEquals).

*Unique Feature*
N/A (yet?)
Launch4j wurde in Betracht gezogen.

*Lessons learned:* Von nun zum Importieren von Libraries *from maven* nutzen, das kann ganze Arbeitstage sparen. 
Ich hätte für Logs und Tours jeweils nur ein ViewModel erstellen müssen, nicht zwei für add und edit. (TODO: korrigieren).

TODO:
sicherstellung von mvvm
.jar to .exe mittels jlink und launch4j
die logs sollten detailreicher angezeigt werden (nicht nur eine spalte/eigenschaft)