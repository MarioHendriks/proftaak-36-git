package com.PTS3S36G3.LobbyService.Models;

public class Lobby
{
    private int id;
    private String name;
    private String description;
    private boolean hasPassword;
    private String password;
    private User userOne;
    private User userTwo;
    private User userThree;
    private User userFour;

    // constructors

    public Lobby(){}

    public Lobby(int id)
    {
        this.id = id;
    }

    public Lobby(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Lobby(int id, String name, String description, boolean hasPassword, String password, User userOne, User userTwo, User userThree, User userFour)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasPassword = hasPassword;
        this.password = password;
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.userThree = userThree;
        this.userFour = userFour;
    }

    // getters && setters

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean getHasPassword()
    {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword)
    {
        this.hasPassword = hasPassword;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User getUserOne()
    {
        return userOne;
    }

    public void setUserOne(User userOne)
    {
        this.userOne = userOne;
    }

    public void setUserOne(int userOneId)
    {
        userOne = new User();
        userOne.setId(userOneId);
    }

    public User getUserTwo()
    {
        return userTwo;
    }

    public void setUserTwo(User userTwo)
    {
        this.userTwo = userTwo;
    }

    public void setUserTwo(int userTwoId)
    {
        userTwo = new User();
        userTwo.setId(userTwoId);
    }

    public User getUserThree()
    {
        return userThree;
    }

    public void setUserThree(User userThree)
    {
        this.userThree = userThree;
    }

    public void setUserThree(int userThreeId)
    {
        userThree = new User();
        userThree.setId(userThreeId);
    }

    public User getUserFour()
    {
        return userFour;
    }

    public void setUserFour(User userFour)
    {
        this.userFour = userFour;
    }

    public void setUserFour(int userFourId)
    {
        userFour = new User();
        userFour.setId(userFourId);
    }
}
