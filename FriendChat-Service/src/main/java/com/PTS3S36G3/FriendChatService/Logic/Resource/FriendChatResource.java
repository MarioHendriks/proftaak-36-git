package com.PTS3S36G3.FriendChatService.Logic.Resource;

import com.PTS3S36G3.FriendChatService.Logic.FriendChatLogic;
import com.PTS3S36G3.FriendChatService.Models.Chat;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/chat")
public class FriendChatResource {

    private FriendChatLogic logic = new FriendChatLogic();

    //in this state of the microservice, this method will return ALL the messages that have been sent in 1 chat.
    @GetMapping(value = "/load/{chatId}")
    public Chat LoadChat(@PathVariable("chatId")int chatId) {
        Chat chat = new Chat();
        chat.setChatId(chatId);
        return logic.LoadChat(chat);
    }

    @GetMapping(value = "/delete/{chatId}")
    public boolean DeleteChat(int chatId) {
        Chat chat = new Chat();
        chat.setChatId(chatId);
        return logic.DeleteChat(chat);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public boolean CreateChat(@RequestBody String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);

        Chat chat = new Chat();

        chat.getUser_one().setId((int) jsonObject.get("user_one"));
        chat.getUser_two().setId((int) jsonObject.get("user_two"));

        return logic.CreateChat(chat);
    }
}
