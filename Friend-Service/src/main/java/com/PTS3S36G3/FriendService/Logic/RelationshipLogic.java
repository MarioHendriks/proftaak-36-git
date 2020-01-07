package com.PTS3S36G3.FriendService.Logic;

        import com.PTS3S36G3.FriendService.DAL.Interfaces.Context.IFriendContext;
        import com.PTS3S36G3.FriendService.DAL.Interfaces.Repositories.IFriendRepository;
        import com.PTS3S36G3.FriendService.DAL.Repositories.FriendRepository;
        import com.PTS3S36G3.FriendService.Factories.FriendFactory;
        import com.PTS3S36G3.FriendService.Models.Relationship;
        import java.util.ArrayList;
        import java.util.List;

public class RelationshipLogic
{
    private IFriendRepository friendRepository;



    public RelationshipLogic()
    {
        friendRepository = new FriendFactory().CreateNewRepo();
    }

    public RelationshipLogic(IFriendContext context)
    {
        friendRepository = new FriendFactory().CreateNewMemoryRepo();
    }

    public List<Relationship> getRelationshipsByUserId(int userId)
    {
        List<Relationship> relationships = friendRepository.GetRelationships();
        List<Relationship> userRelationships = new ArrayList<>();
        for(Relationship relationship : relationships)
        {
            if(relationship.getFriendOne() == userId || relationship.getFriendTwo() == userId){
                userRelationships.add(relationship);
            }
        }
        return userRelationships;
    }

    public boolean AddFriend(int userId, int friendId) { return friendRepository.AddFriend(userId, friendId); }

    public boolean DeleteFriend(int userId, int friendId){ return friendRepository.DeleteFriend(userId, friendId);}

    public boolean AcceptFriend(int userId, int friendId){ return friendRepository.AcceptFriend(userId, friendId);}

    public boolean DeclineFriend(int userId, int friendId){ return friendRepository.DeclineFriend(userId, friendId);}
}
