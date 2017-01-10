package com.jerait.lifecontrol.desktop.utils;

import com.jerait.lifecontrol.desktop.controller.AuthController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * GUI utils class for fxml.
 */
public final class GUI {

    /**
     * Close default constructor.
     */
    private GUI() {

    }

    /**
     * Get resource url from view name.
     * @param viewName fxml view (file) name without extension.
     * @return resource url.
     */
    private static java.net.URL getResourceUrl(final String viewName) {
        return GUI.class.getResource("/view/" + viewName + ".fxml");
    }

    /**
     * Prepare scene from fxml resource.
     * @param viewName fxml name withount extensions from views folder.
     * @return Scene object.
     * @throws IOException on load errors.
     */
    public static Scene getScene(final String viewName) throws IOException {
        Parent root = FXMLLoader.load(getResourceUrl(viewName));

        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Main.css");

        return scene;
    }

    /**
     * Show custom modal dialog from fxml file.
     * @param viewName fxml view (file) name without extension.
     * @param primaryStage parent Stage for modal dialog.
     * @return true, if Ok button pressed, false another.
     * @throws IOException on load errors.
     */
    public static Boolean showDialog(
        final String viewName,
        final Stage primaryStage
    ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getResourceUrl(viewName));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();

        dialogStage.setTitle(viewName);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        AuthController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isOkClicked();
    }
}
