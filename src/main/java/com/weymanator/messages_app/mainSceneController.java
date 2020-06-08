package com.weymanator.messages_app;

import com.weymanator.messages_app.components.ChatPreview;
import com.weymanator.messages_app.components.MessageGlobe;
import com.weymanator.messages_app.db.Manager;
import com.weymanator.messages_app.db.exceptions.NoUserFoundException;
import com.weymanator.messages_app.db.models.Chat;
import com.weymanator.messages_app.db.models.Message;
import com.weymanator.messages_app.db.models.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.text.SimpleDateFormat;
import java.util.Stack;

public class mainSceneController {
    private Manager dbManager;
    private Stack<ChatPreview> chats;
    private User user;
    private Stack<StackPane> messages;
    private ChatPreview currentPreview;

    @FXML
    private VBox chatsContainer;
    @FXML
    private GridPane chatHeader;
    @FXML
    private Label userName;
    @FXML
    private StackPane inputContainer;
    @FXML
    private StackPane avatarContainer;
    @FXML
    private VBox messagesContainer;
    @FXML
    private TextField textInput;

    public void initialize() {
        chatHeader.setOpacity(0);
        inputContainer.setOpacity(0);

        dbManager = new Manager();
        chats = new Stack<>();
        try {
            user = dbManager.getUserByID(1);
            for (Chat item : user.getChats()) {
                ChatPreview preview = new ChatPreview();
                preview.setID(item.getID());
                preview.setUserName(item.getName());
                Stack<Message> _messages = item.getMessages();
                Message lastMessage = _messages.get(_messages.size() - 1);
                preview.setDate(lastMessage.getDate());
                preview.setMessage(lastMessage.getMessage());
                preview.setAvatar(item.getAvatar());
                preview.setOnClick((event) -> {
                    openChat(item);
                });
                chats.add(preview);
            }
            chatsContainer.getChildren().addAll(chats);
        } catch (NoUserFoundException exception) {
            throw new RuntimeException(exception);
        }

        textInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String message = textInput.getText();
                    if (message.trim().length() != 0) {
                        sendMessage(message);
                    }
                }
            }
        });
    }

    private void sendMessage(String message) {
        String date = new SimpleDateFormat("MM/dd").format(System.currentTimeMillis());
        StackPane row = new StackPane();
        row.setStyle("-fx-padding: 22");
        MessageGlobe globe;
        row.setAlignment(Pos.CENTER_RIGHT);
        globe = new MessageGlobe(true);
        globe.setDate(date);
        globe.setMessage(message);
        row.getChildren().add(globe);
        messagesContainer.getChildren().add(row);
        textInput.clear();
        currentPreview.setMessage(message);
        currentPreview.setDate(date);
    }

    private void setAvatar(String url) {
        Image avatarImage = new Image(url);
        Circle avatarCircle = new Circle(45, 45, 45 / 2);
        avatarCircle.setFill(new ImagePattern(avatarImage));
        avatarContainer.getChildren().add(avatarCircle);
    }

    private void toggleIfNotIs(Chat chat) {
        for (ChatPreview item : chats) {
            if (item.getState() && item.getID() != chat.getID()) {
                item.toggleState();
            }
        }
    }

    private void setMessages(Chat chat) {
        messagesContainer.getChildren().clear();
        messages = new Stack<>();
        for (Message item: chat.getMessages()) {
            StackPane row = new StackPane();
            row.setStyle("-fx-padding: 22");
            MessageGlobe globe;
            if (item.getSender().getID() == user.getID()) {
                row.setAlignment(Pos.CENTER_RIGHT);
                globe = new MessageGlobe(true);
            } else {
                row.setAlignment(Pos.CENTER_LEFT);
                globe = new MessageGlobe();
            }
            globe.setDate(item.getDate());
            globe.setMessage(item.getMessage());
            row.getChildren().add(globe);
            messages.add(row);
        }
        messagesContainer.getChildren().addAll(messages);
    }

    private void openChat(Chat chat) {
        chatHeader.setOpacity(1);
        inputContainer.setOpacity(1);

        setAvatar(chat.getAvatar());
        userName.setText(chat.getName());

        setMessages(chat);

        toggleIfNotIs(chat);

        setCurrentPreview(chat);
    }

    private void setCurrentPreview(Chat chat) {
        for (ChatPreview item : chats) {
            if (item.getID() == chat.getID()) {
                currentPreview = item;
                break;
            }
        }
    }
}
