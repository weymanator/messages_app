module messages_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.weymanator.messages_app to javafx.fxml;
    opens com.weymanator.messages_app.components to javafx.fxml;
    exports com.weymanator.messages_app;
    exports com.weymanator.messages_app.components;
    exports com.weymanator.messages_app.db.models;
}