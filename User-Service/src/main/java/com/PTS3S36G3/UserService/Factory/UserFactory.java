package com.PTS3S36G3.UserService.Factory;

import com.PTS3S36G3.UserService.DAL.Contexts.UserContextSQLtest;
import com.PTS3S36G3.UserService.DAL.Interfaces.Repositories.IUserRepository;
import com.PTS3S36G3.UserService.DAL.Repositories.UserRepository;

public class UserFactory
{
    private UserRepository userRepository;

    public IUserRepository CreateNewRepo()
    {
        userRepository = new UserRepository();
        return userRepository;
    }

    public IUserRepository CreateNewMemoryRepo()
    {
        userRepository = new UserRepository(new UserContextSQLtest());
        return userRepository;
    }
}
