package com.PTS3S36G3.FriendService.DAL.Interfaces.Context;

import com.PTS3S36G3.FriendService.Models.Relationship;

import java.util.List;

public interface IFriendContext
{
    List<Relationship> GetRelationships();
    boolean AddFriend(int userId, int friendId);
    boolean DeleteFriend(int userId, int friendId);
    boolean AcceptFriend(int userId, int friendId);
    boolean DeclineFriend(int userId, int friendId);

}
