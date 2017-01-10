package com.jerait.lifecontrol.desktop;

import com.jerait.lifecontrol.desktop.utils.Database;
import com.jerait.lifecontrol.desktop.utils.GUI;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class MainApp extends Application {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final Stage stage) throws Exception {
        stage.setTitle("LifeControl v0.0.1");

        if (!Database.exists()) {
            Database.initDatabase();
        }

        Scene scene = GUI.getScene("Main");

        stage.setScene(scene);
        stage.show();

        if (!GUI.showDialog("Auth", stage)) {
            stage.close();
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
