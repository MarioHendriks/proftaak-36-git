package com.PTS3S36G3.UserService.Logic;

import com.PTS3S36G3.UserService.DAL.Interfaces.Context.IUserContext;
import com.PTS3S36G3.UserService.DAL.Interfaces.Repositories.IUserRepository;
import com.PTS3S36G3.UserService.Factory.UserFactory;
import com.PTS3S36G3.UserService.Models.User;
import com.PTS3S36G3.UserService.DAL.Repositories.UserRepository;

public class UserLogic
{
    private IUserRepository userRepository;

    public UserLogic()
    {
        userRepository = new UserFactory().CreateNewRepo();
    }

    public UserLogic(IUserContext testcontext)
    {
        this.userRepository = new UserFactory().CreateNewMemoryRepo();
    }



    public User GetUserById(int userId)
    {
        return userRepository.GetUserById(userId);
    }

    public User GetUserByUsername(String username)
    {
        return userRepository.GetUserByUsername(username);
    }

    public User GetUserByEmail(String email)
    {
        return userRepository.GetUserByEmail(email);
    }

    public User AddUser(User user)
    {
        User checkUser = GetUserByUsername(user.getName());

        if (checkUser.getId() == 0)
        {
            return userRepository.AddUser(user);
        }

        return new User(0, "Username Taken");
    }

    public boolean DeleteTestUser()
    {
        return userRepository.DeleteUser("ewoxRxHtXSTXMMotKBhBqcQjoLQh3P4HM7fMkI3btiPjTVipnnTmwyErJYcbYR0fPbF73iCn9AQAcSq3rADnpIroUSf4qto5baNK");
    }
}
