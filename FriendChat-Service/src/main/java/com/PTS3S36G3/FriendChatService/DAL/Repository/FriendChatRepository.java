package com.PTS3S36G3.FriendChatService.DAL.Repository;

import com.PTS3S36G3.FriendChatService.DAL.Context.FriendChatContext;
import com.PTS3S36G3.FriendChatService.DAL.Interface.IFriendChatContext;
import com.PTS3S36G3.FriendChatService.Models.Chat;

public class FriendChatRepository implements IFriendChatContext {

    private IFriendChatContext context;
    public FriendChatRepository(){context = new FriendChatContext();}


    @Override
    public Chat LoadChat(Chat chat) {
        return context.LoadChat(chat);
    }

    @Override
    public boolean DeleteChat(Chat chat) {
        return context.DeleteChat(chat);
    }

    @Override
    public boolean CreateChat(Chat chat) {
        return context.CreateChat(chat);
    }
}
