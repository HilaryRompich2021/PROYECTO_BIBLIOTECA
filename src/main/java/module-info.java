module com.mycompany.proyecto_biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires java.desktop;
    requires java.logging;
    requires javafx.media;
    requires javafx.web;
    requires javafx.graphics;
    requires java.sql;
    
    opens ClasesJava to javafx.base; // Esta línea permite que javafx.base acceda a ClasesJava

    opens com.mycompany.proyecto_biblioteca to javafx.fxml;
    exports com.mycompany.proyecto_biblioteca;
    requires javafx.graphicsEmpty;
}
