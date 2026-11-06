module icesi.demovehicleti {
    requires javafx.controls;
    requires javafx.fxml;

    opens icesi.demovehicleti to javafx.fxml;
    opens control to javafx.fxml;
    opens model to javafx.base;
    exports icesi.demovehicleti;
}