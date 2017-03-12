package com.jerait.lifecontrol.desktop.controller;

import java.util.HashMap;
import javafx.stage.Stage;

/**
 *
 * @author sergzx
 */
public class DialogController {
    /**
     * Contained dialog Stage loaded from fxml view.
     */
    private Stage dialogStage;

    /**
     * Contained dialog result after close.
     */
    private boolean okClicked = false;

    /**
     * Contained dialog additional data.
     */
    protected HashMap data = null;

    /**
     * Check dialog result.
     * @return true, if Enter button (Ok) pressed.
     */
    public final boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Set dialog Stage into property.
     * @param stage dialog Stage instance.
     */
    public final void setDialogStage(final Stage stage) {
        stage.resizableProperty().set(false);
        dialogStage = stage;
    }

    /**
     * Close current dialog window.
     * @param isOkClicked true, if Ok button clicked, false another.
     */
    public final void closeDialog(final Boolean isOkClicked) {
        okClicked = isOkClicked;
        dialogStage.close();
    }

    /**
     * Set dialog data.
     * @param newData dialog data.
     */
    public final void setData(HashMap newData) {
        data = newData;
    }
}
