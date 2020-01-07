package com.PTS3S36G3.FriendService.Models;

import com.PTS3S36G3.FriendService.Models.Enums.Status;

public class Relationship
{
    private int friendId;
    private int friendOne;
    private int friendTwo;
    private Status status;

    public Relationship()
    {

    }

    public Relationship(int friendOne, int friendTwo)
    {
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
    }

    public Relationship(int friendId, int friendOne, int friendTwo, Status status)
    {
        this.friendId = friendId;
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
        this.status = status;
    }

    public int getFriendId()
    {
        return friendId;
    }

    public void setFriendId(int friendId)
    {
        this.friendId = friendId;
    }

    public int getFriendOne()
    {
        return friendOne;
    }

    public void setFriendOne(int friendOne)
    {
        this.friendOne = friendOne;
    }

    public int getFriendTwo()
    {
        return friendTwo;
    }

    public void setFriendTwo(int friendTwo)
    {
        this.friendTwo = friendTwo;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
