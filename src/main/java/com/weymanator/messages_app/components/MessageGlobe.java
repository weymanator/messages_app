package com.weymanator.messages_app.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MessageGlobe extends GridPane {

    @FXML
    private ColumnConstraints mainContainer;
    @FXML
    private Label date;
    @FXML
    private StackPane messageContainer;
    @FXML
    private Label message;

    public MessageGlobe() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("messageGlobe.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MessageGlobe(Boolean right) {
        this();
        mainContainer.setHalignment(HPos.LEFT);
        messageContainer.setAlignment(Pos.BASELINE_RIGHT);
    }

    public void setMessage(String _message) {
        message.setText(_message);
    }

    public void setDate(String _date) {
        date.setText(_date);
    }
}
