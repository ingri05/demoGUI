package icesi.demovehicleti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                App.class.getResource("menu-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("SGMMS - Main Menu");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

