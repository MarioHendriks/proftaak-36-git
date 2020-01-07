package com.PTS3S36G3.FriendService.DAL.Repositories;

import com.PTS3S36G3.FriendService.DAL.Contexts.FriendContextMemory;
import com.PTS3S36G3.FriendService.DAL.Contexts.FriendContextSQL;
import com.PTS3S36G3.FriendService.DAL.Interfaces.Context.IFriendContext;
import com.PTS3S36G3.FriendService.DAL.Interfaces.Repositories.IFriendRepository;
import com.PTS3S36G3.FriendService.Models.Relationship;

import java.util.List;

public class FriendRepository implements IFriendRepository
{
    private IFriendContext friendContext;

    //constructors
    public FriendRepository(IFriendContext context)
    {
        friendContext = context;
    }

    //methods
    public List<Relationship> GetRelationships()
    {
        return friendContext.GetRelationships();
    }

    public boolean AddFriend(int userId, int friendId){ return friendContext.AddFriend(userId, friendId);}

    public boolean DeleteFriend(int userId, int friendId){ return friendContext.DeleteFriend(userId, friendId);}

    public boolean AcceptFriend(int userId, int friendId){ return friendContext.AcceptFriend(userId, friendId);}

    public boolean DeclineFriend(int userId, int friendId){ return friendContext.DeclineFriend(userId, friendId);}
}


