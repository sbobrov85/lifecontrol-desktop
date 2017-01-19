package com.jerait.lifecontrol.desktop;

import com.jerait.lifecontrol.desktop.utils.Database;
import com.jerait.lifecontrol.desktop.utils.GUI;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class MainApp extends Application {

    @Override
    public final void init() throws Exception {
      if (!Database.exists()) {
          Database.initDatabase();
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final Stage stage) throws Exception {
        stage.setTitle("LifeControl v0.0.1");

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
        LauncherImpl.launchApplication(
            MainApp.class,
            MainPreloader.class,
            args
        );
    }

}
