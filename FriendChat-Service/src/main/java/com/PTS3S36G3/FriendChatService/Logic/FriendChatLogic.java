package com.PTS3S36G3.FriendChatService.Logic;

import com.PTS3S36G3.FriendChatService.DAL.Repository.FriendChatRepository;
import com.PTS3S36G3.FriendChatService.Models.Chat;
import com.PTS3S36G3.FriendChatService.Models.Message;
import com.PTS3S36G3.FriendChatService.Models.User;

public class FriendChatLogic {

    private FriendChatRepository repository = new FriendChatRepository();

    public Chat LoadChat(Chat chat) {
        User user_one = chat.getUser_one();
        for(Message message: chat.getAll_messages())
        {
            if(message.getUserid() == user_one.getId())
            {
                chat.AddMessageToFriendOneMessageList(message);
            }
            else {
                chat.AddMessageToFriendTwoMessageList(message);
            }
        }
        return repository.LoadChat(chat);
    }

    public boolean DeleteChat(Chat chat) {
        return repository.DeleteChat(chat);
    }

    public boolean CreateChat(Chat chat) {
        return repository.CreateChat(chat);
    }
}
