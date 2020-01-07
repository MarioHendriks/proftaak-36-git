package com.PTS3S36G3.FriendService.Factories;

import com.PTS3S36G3.FriendService.DAL.Contexts.FriendContextMemory;
import com.PTS3S36G3.FriendService.DAL.Contexts.FriendContextSQL;
import com.PTS3S36G3.FriendService.DAL.Interfaces.Repositories.IFriendRepository;
import com.PTS3S36G3.FriendService.DAL.Repositories.FriendRepository;

public class FriendFactory
{
    private FriendRepository friendRepository;
    public IFriendRepository CreateNewRepo()
    {
        friendRepository = new FriendRepository(new FriendContextSQL());
        return friendRepository;
    }

    public IFriendRepository CreateNewMemoryRepo()
    {
        friendRepository = new FriendRepository(new FriendContextMemory());
        return friendRepository;
    }

}
