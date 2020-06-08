package com.weymanator.messages_app.db.models;

import java.util.Stack;

public class Chat {
    public Integer ID;
    public String Avatar;
    public String Name;
    public Stack<User> Members;
    public Stack<Message> Messages;

    public Chat() {}

    public Chat(Integer id, String name, Stack<User> members, Stack<Message> messages, String avatar) {
        ID = id;
        Avatar = avatar;
        Name = name;
        Members = members;
        Messages = messages;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setMembers(Stack<User> members) {
        Members = members;
    }

    public void setMessages(Stack<Message> messages) {
        Messages = messages;
    }

    public String getName() {
        return Name;
    }

    public Integer getID() {
        return ID;
    }

    public Stack<User> getMembers() {
        return Members;
    }

    public Stack<Message> getMessages() {
        return Messages;
    }

    public String getAvatar() {
        return Avatar;
    }
}
