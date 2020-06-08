package com.weymanator.messages_app.db.models;

import java.util.Stack;

public class User {
    public Integer ID;
    public String Avatar;
    public String Name;
    public Stack<User> Contacts;
    public Stack<Chat> Chats;

    public User() {}

    public User(Integer id, String name, Stack<User> contacts, Stack<Chat> chats, String avatar) {
        Avatar = avatar;
        ID = id;
        Name = name;
        Contacts = contacts;
        Chats = chats;
    }

    public void addContact(User contact) {
        Contacts.push(contact);
    }

    public void setContacts(Stack<User> contacts) {
        Contacts = contacts;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setChats(Stack<Chat> chats) {
        Chats = chats;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Stack<User> getContacts() {
        return Contacts;
    }

    public Stack<Chat> getChats() {
        return Chats;
    }

    public String getAvatar() {
        return Avatar;
    }
}
