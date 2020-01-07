package com.PTS3S36G3.FriendService.Logic.Resources;

import com.PTS3S36G3.FriendService.DAL.Contexts.FriendContextMemory;
import com.PTS3S36G3.FriendService.Logic.RelationshipLogic;
import com.PTS3S36G3.FriendService.Models.Enums.Status;
import com.PTS3S36G3.FriendService.Models.Friends;
import com.PTS3S36G3.FriendService.Models.Relationship;
import com.PTS3S36G3.FriendService.Models.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/private/friend")
public class FriendResource
{
    @Autowired
    private RestTemplate restTemplate;

    private RelationshipLogic relationshipLogic;

    private Friends friends = new Friends();

    public FriendResource() {
        this.relationshipLogic = new RelationshipLogic();
    }

    public FriendResource(RelationshipLogic relationshipLogic)
    {
        this.relationshipLogic = new RelationshipLogic(new FriendContextMemory());
    }


   // @HystrixCommand(fallbackMethod = "GetFallbackAllFriends")
    @GetMapping(value = "/get/{userId}")
    public Friends GetFriends(@PathVariable("userId") int userId)
    {
        friends = new Friends();
        // Get all relationships with the requesting user involved.
        List<Relationship> userRelationships = relationshipLogic.getRelationshipsByUserId(userId);
        // friend list.
        for (Relationship relationship : userRelationships)
        {
            User friend = new User();

            // Check whether the user is friendOne or friendTwo.
            if (userId == relationship.getFriendOne())
            {
                String url = "http://user-service.default/api/private/user/getById/" + relationship.getFriendTwo();
                friend = CreateRequest(url);
            } else if (userId == relationship.getFriendTwo())
            {
                String url = "http://user-service.default/api/private/user/getById/" + relationship.getFriendOne();
                friend = CreateRequest(url);
            }

            // Add the friend to the correct list depending on the status.
            friends.addFriend(friend, relationship.getStatus());
        }
        
        return friends;
    }

    private User CreateRequest(String url)
    {
        HttpHeaders headers = new HttpHeaders();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik5rTkNRelU0UVRsQ016WTJSVFUzT1VOQ1FVUTRNREU0UmtVMU9FSTFSVEExTVVRNE5VUTBRUSJ9.eyJpc3MiOiJodHRwczovL2RlYXRocnVuLmF1dGgwLmNvbS8iLCJzdWIiOiJnb29nbGUtb2F1dGgyfDEwMDc5MzUzNDgxMTMyNTgwMjMyOSIsImF1ZCI6WyJodHRwczovL0RlYXRocnVuQXBpIiwiaHR0cHM6Ly9kZWF0aHJ1bi5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNTc2NjU5OTI5LCJleHAiOjE1NzY3NDYzMjksImF6cCI6IlBYOTIyT0JncTZlU1FXRW1ZNFdkSnpPRW44emhua2hIIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.eFeYDpR5zaQMUd_8URfOrE0h899YH3OR_Wu3Hvtrp0T2qq2SfHhblpDRbrTMtAALvCKqrAhGtGxa3jAD-SSd1uV2Kp4bkv28h1tCKqGCBu8P5DGB4JRnZuMRAIJpCMAVNXaWMgOGuKUoUcyQ6xNbt8OOav-rLNdbZVNibptnOLJKYUOSYfM6WX2VF2cS8AO8BVJJM65jJkzuvfwMMooM-Tk-s4J1CaiJ3W2jmT0z07ct5b3UhBIemSFpyOv3ed-tfHzwCO1BiTzYyTXpLpk8b13m5D3trrwzL6DVme4njHbH0xZd-Ky9irvZt2UIgVz6OqwZO0Dbh5qLwfEhkHEoog";
        headers.setBearerAuth(token);

        // todo get the token from the front-end request and pass it to here
        // Make the request and return the response as a User object
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, entity, User.class);

        return response.getBody();
    }

    public Friends GetFallbackAllFriends(@PathVariable("userId") int userId)
    {
        // Return custom hardcoded friends model when this fallback method gets called.
        User user = new User();
        user.setId(0);
        user.setEmail("");
        user.setName("User not found");

        friends.addFriend(user, Status.Accepted);
        return friends;
    }

    @PostMapping(value = "/add")
    public @ResponseBody boolean AddFriend(@RequestBody Relationship relationship)
    {
        return relationshipLogic.AddFriend(relationship.getFriendOne(),relationship.getFriendTwo());
    }

    @PostMapping(value = "/delete")
    public @ResponseBody boolean DeleteFriend(@RequestBody Relationship relationship)
    {
        return relationshipLogic.DeleteFriend(relationship.getFriendOne(),relationship.getFriendTwo());
    }

    @PostMapping(value = "/accept")
    public @ResponseBody boolean AcceptFriend(@RequestBody Relationship relationship)
    {
        return relationshipLogic.AcceptFriend(relationship.getFriendOne(),relationship.getFriendTwo());
    }

    @PostMapping(value = "/decline")
    public @ResponseBody boolean DeclineFriend(@RequestBody Relationship relationship)
    {
        return relationshipLogic.DeclineFriend(relationship.getFriendOne(),relationship.getFriendTwo());
    }
}
