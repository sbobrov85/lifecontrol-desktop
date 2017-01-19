package com.jerait.lifecontrol.desktop;

import com.jerait.lifecontrol.desktop.utils.GUI;
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main preloader class.
 */
public class MainPreloader extends Preloader {

  /**
   * Contain preloader stage object.
   */
  private Stage preloaderStage;

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    preloaderStage = primaryStage;
    preloaderStage.initStyle(StageStyle.UNDECORATED);
    Scene mainPreloader = GUI.getScene("MainPreloader");
    preloaderStage.setScene(mainPreloader);
    preloaderStage.show();
  }

  /**
   * {@inheritDoc}
   */
  @Override
   public void handleStateChangeNotification(
      StateChangeNotification stateChangeNotification
    ) {
      if (stateChangeNotification.getType() == Type.BEFORE_START) {
         preloaderStage.hide();
      }
   }
}
