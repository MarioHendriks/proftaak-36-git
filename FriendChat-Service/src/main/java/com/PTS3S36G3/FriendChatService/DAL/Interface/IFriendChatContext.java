package com.PTS3S36G3.FriendChatService.DAL.Interface;

import com.PTS3S36G3.FriendChatService.Models.Chat;

public interface IFriendChatContext {
    Chat LoadChat(Chat chat);
    boolean DeleteChat(Chat chat);
    boolean CreateChat(Chat chat);
}
