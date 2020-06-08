package com.weymanator.messages_app.components;

import com.weymanator.messages_app.db.models.Chat;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.function.Consumer;

public class ChatPreview extends GridPane {
    public static Boolean STATE_ACTIVE = true;
    public static Boolean STATE_INACTIVE = false;

    @FXML
    private StackPane avatarContainer;
    @FXML
    private Label userName;
    @FXML
    private Label date;
    @FXML
    private Label message;

    private Boolean state = STATE_INACTIVE;
    private Integer ID;

    public ChatPreview() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatPreview.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setMessage(String _message) {
        message.setText(_message);
    }

    public void setUserName(String _username) {
        userName.setText(_username);
    }

    public void setDate(String _date) {
        date.setText(_date);
    }

    public void setAvatar(String url) {
        Image avatarImage = new Image(url);
        Circle avatarCircle = new Circle(45, 45, 45 / 2);
        avatarCircle.setFill(new ImagePattern(avatarImage));
        avatarContainer.getChildren().add(avatarCircle);
    }

    public void setOnClick(Consumer<MouseEvent> callback) {
        EventHandler<MouseEvent> eventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (state == STATE_INACTIVE) toggleState();
                callback.accept(event);
            }
        };

        this.addEventFilter(MouseEvent.MOUSE_CLICKED, eventEventHandler);
    }

    public void toggleState() {
        state = !state;
        if (state == STATE_ACTIVE) {
            this.setStyle("-fx-background-color: #4A4848;");
        }

        if (state == STATE_INACTIVE) {
            this.setStyle("-fx-background-color: #2D2C2C;");
        }
    }

    public Boolean getState() {
        return state;
    }

    public Integer getID() {
        return ID;
    }
}
