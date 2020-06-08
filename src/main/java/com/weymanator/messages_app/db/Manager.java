package com.weymanator.messages_app.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weymanator.messages_app.db.exceptions.NoUserFoundException;
import com.weymanator.messages_app.db.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class Manager {
    private Stack<User> Users;

    public Manager() {
        try {
            File file = new File(getClass().getClassLoader().getResource("mockData.json").getFile());
            ObjectMapper mapper = new ObjectMapper();
            Users = mapper.readValue(file, new TypeReference<Stack<User>>(){});
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public User getUserByID(Integer ID) throws NoUserFoundException {
        Stack<User> result = new Stack<>();
        for (User item : Users) {
            if (item.getID() == ID) {
                result.add(item);
            }
        }

        try {
            User found = result.get(0);
            return found;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new NoUserFoundException("User with ID: " + ID + " not found");
        }
    }
}
