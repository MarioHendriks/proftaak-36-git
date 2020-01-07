package com.PTS3S36G3.UserService.DAL.Repositories;

import com.PTS3S36G3.UserService.DAL.Contexts.UserContextSQL;
import com.PTS3S36G3.UserService.DAL.Contexts.UserContextSQLtest;
import com.PTS3S36G3.UserService.DAL.Interfaces.Context.IUserContext;
import com.PTS3S36G3.UserService.DAL.Interfaces.Repositories.IUserRepository;
import com.PTS3S36G3.UserService.Models.User;

public class UserRepository implements IUserRepository
{
    private IUserContext userContext;

    public UserRepository(){
        userContext = new UserContextSQL();
    }

    public UserRepository(IUserContext context)
    {
        userContext = new UserContextSQLtest();
    }



    public User AddUser(User user){ return userContext.AddUser(user); }

    public User GetUserByUsername(String username)
    {
        return userContext.GetUserByUsername(username);
    }

    public User GetUserById(int userId){ return userContext.GetUserById(userId); }

    public User GetUserByEmail(String email) { return userContext.GetUserByEmail(email); }

    public boolean DeleteUser(String name) { return userContext.DeleteUser(name); }
}
