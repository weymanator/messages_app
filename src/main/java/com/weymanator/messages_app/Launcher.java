package com.weymanator.messages_app;

import com.weymanator.messages_app.db.Manager;
import com.weymanator.messages_app.db.exceptions.NoUserFoundException;
import com.weymanator.messages_app.db.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Manager dbManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        // root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
