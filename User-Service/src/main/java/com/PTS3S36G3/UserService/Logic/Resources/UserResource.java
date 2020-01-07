package com.PTS3S36G3.UserService.Logic.Resources;

import com.PTS3S36G3.UserService.DAL.Contexts.UserContextSQLtest;
import com.PTS3S36G3.UserService.Models.User;
import com.PTS3S36G3.UserService.Logic.UserLogic;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user")
public class UserResource
{
    private UserLogic userLogic;

    public UserResource()
    {
        this.userLogic = new UserLogic();
    }

    public UserResource(UserLogic userLogic)
    {
        this.userLogic = new UserLogic(new UserContextSQLtest());
    }



    @GetMapping(value = "/getById/{userId}")
    public User GetUserById(@PathVariable("userId")int userId)
    {
        return userLogic.GetUserById(userId);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public User GetUserByEmail(@PathVariable("email")String email)
    {
        return userLogic.GetUserByEmail(email);
    }

    @PostMapping(value = "/createUser", consumes = "application/json", produces = "application/json")
    public User AddUser(@RequestBody String profile)
    {
        JSONObject jsonObject = new JSONObject(profile);
        User user = new User(jsonObject.getString("username"), jsonObject.getString("email"));
        return userLogic.AddUser(user);
    }
}