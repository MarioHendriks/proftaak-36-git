package com.PTS3S36G3.FriendService.Models;

import com.PTS3S36G3.FriendService.Models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Friends
{
    private List<User> pendingFriends;
    private List<User> blockedFriends;
    private List<User> acceptedFriends;

    public Friends()
    {
        this.pendingFriends = new ArrayList<>();
        this.blockedFriends = new ArrayList<>();
        this.acceptedFriends = new ArrayList<>();
    }

    public void addFriend(User friend, Status status){
        if (status.equals(Status.Pending)){
            pendingFriends.add(friend);
        }
        else if (status.equals(Status.Accepted)){
            acceptedFriends.add(friend);
        }
        else if (status.equals(Status.Declined)){
            blockedFriends.add(friend);
        }
    }

    public List<User> getPendingFriends() {
        return pendingFriends;
    }

    public void setPendingFriends(List<User> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    public List<User> getBlockedFriends() {
        return blockedFriends;
    }

    public void setBlockedFriends(List<User> blockedFriends) {
        this.blockedFriends = blockedFriends;
    }

    public List<User> getAcceptedFriends() {
        return acceptedFriends;
    }

    public void setAcceptedFriends(List<User> acceptedFriends) {
        this.acceptedFriends = acceptedFriends;
    }
}
