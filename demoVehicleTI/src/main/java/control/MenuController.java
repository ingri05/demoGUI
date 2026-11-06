package control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MenuController {

    @FXML
    private void openVehicleRegistration(ActionEvent event)
            throws IOException {

        openVehicleView(event);
    }

    @FXML
    private void openVehicleList(ActionEvent event)
            throws IOException {

        openVehicleView(event);
    }

    private void openVehicleView(ActionEvent event)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/icesi/demovehicleti/vehicle-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(scene);
        stage.setTitle("SGMMS - Vehicle Management");
        stage.centerOnScreen();
    }

    @FXML
    private void exitApplication() {
        Platform.exit();
    }
}
