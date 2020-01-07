package com.PTS3S36G3.UserService.DAL.Interfaces.Repositories;

import com.PTS3S36G3.UserService.Models.User;

public interface IUserRepository
{
    User GetUserById(int userId);

    User GetUserByUsername(String username);

    User GetUserByEmail(String email);

    User AddUser(User user);

    boolean DeleteUser(String name);
}
