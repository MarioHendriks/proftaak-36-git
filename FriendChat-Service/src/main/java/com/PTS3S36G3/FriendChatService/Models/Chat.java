package com.PTS3S36G3.FriendChatService.Models;

import java.util.List;

public class Chat
{
    int ChatId;
    User User_one;
    User User_two;
    List<Message> all_messages;
    List<Message> messages_user_one;
    List<Message> messages_user_two;

    public Chat() { }

    public Chat(int chatId, User user_one, User user_two, List<Message> messages, List<Message> messages_user_one, List<Message> messages_user_two) {
        ChatId = chatId;
        User_one = user_one;
        User_two = user_two;
        this.all_messages = messages;
        this.messages_user_one = messages_user_one;
        this.messages_user_two = messages_user_two;
    }
    public int getChatId(){return ChatId;}

    public void setChatId(int chatId){ChatId = chatId;}

    public User getUser_one() { return User_one; }

    public void setUser_one(User user_one) {
        User_one = user_one;
    }

    public User getUser_two() {
        return User_two;
    }

    public void setUser_two(User user_two) {
        User_two = user_two;
    }

    public List<Message> getAll_messages() {
        return all_messages;
    }

    public void setAll_messages(List<Message> all_messages) {
        this.all_messages = all_messages;
    }

    public List<Message> getMessages_user_one() {
        return messages_user_one;
    }

    public void setMessages_user_one(List<Message> messages_user_one) {
        this.messages_user_one = messages_user_one;
    }

    public List<Message> getMessages_user_two() {
        return messages_user_two;
    }

    public void setMessages_user_two(List<Message> messages_user_two) {
        this.messages_user_two = messages_user_two;
    }

    public void AddMessageToAllMessagesList(Message message){ all_messages.add(message);}
    public void AddMessageToFriendOneMessageList(Message message){ messages_user_one.add(message);}
    public void AddMessageToFriendTwoMessageList(Message message){ messages_user_two.add(message);}
}
