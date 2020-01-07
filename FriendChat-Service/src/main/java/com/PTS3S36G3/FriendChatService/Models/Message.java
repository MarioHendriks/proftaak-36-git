package com.PTS3S36G3.FriendChatService.Models;

import java.time.LocalDateTime;
import java.util.Timer;

public class Message {
    int Userid;
    String message;
    String TimeStamp;

    public Message() {
    }

    public Message(int userid, String message, String timeStamp) {
        Userid = userid;
        this.message = message;
        TimeStamp = timeStamp;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
