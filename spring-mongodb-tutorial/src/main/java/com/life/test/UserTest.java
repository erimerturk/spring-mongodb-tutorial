package com.life.test;

import java.util.List;
import java.util.UUID;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.life.domain.Plan;
import com.life.domain.User;
import com.life.domain.relationship.Relationship;
import com.life.service.PlanService;
import com.life.service.UserService;
import com.life.service.friend.impl.FriendServiceImpl;

public class UserTest
{

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-resources.xml");
        BeanFactory factory = context;

        createUser(factory);
        getUserList(factory);

        loginTest(factory);

    }

    private static void loginTest(BeanFactory factory)
    {
        UserService userService = (UserService) factory.getBean("userService");
        // Auth auth = userService.login("bywyypgqorighfa", "discovored");
        List<User> users = userService.readAll();
        System.out.println(1);

    }

    private static void getUserList(BeanFactory factory)
    {
        // UserService userService = (UserService)
        // factory.getBean("userService");
        // for(User user : userService.getUsers())
        // {
        // // System.out.println(user.toString());
        // }

    }

    private static void createUser(BeanFactory factory)
    {
        DataFactory df = new DataFactory();

        for (int i = 0; i < 1; i++)
        {
            User user = createUser(df);

            UserService userService = (UserService) factory.getBean("userService");
            User persistendUser = userService.create(user);

            createUserFriendRelationship(persistendUser.getId(), df, factory);

            Plan plan = createPlan();
            PlanService planService = (PlanService) factory.getBean("planService");
            planService.create(persistendUser, plan);

            getUserFriends(persistendUser.getId(), factory);
            //
        }

    }

    private static void getUserFriends(String id, BeanFactory factory)
    {
        FriendServiceImpl friendService = (FriendServiceImpl) factory.getBean("friendService");
        List<User> friends = friendService.getUserFriendList(id);

        for (User friend : friends)
        {
            System.out.println(friend.toString());
        }

    }

    private static void createUserFriendRelationship(String userId, DataFactory df, BeanFactory factory)
    {
        // TODO Auto-generated method stub

        FriendServiceImpl friendService = (FriendServiceImpl) factory.getBean("friendService");
        Relationship friendRelationship = getFriendship(friendService, userId, factory);

        User friend = createFriend(df);
        User friend2 = createFriend(df);
        friendRelationship.addFriend(friend);
        friendRelationship.addFriend(friend2);

        friendService.createRelationship(friendRelationship);

    }

    private static Relationship getFriendship(FriendServiceImpl friendService, String userId, BeanFactory factory)
    {
        Relationship friendRelationship = friendService.findByUserId(userId);

        if (friendRelationship == null)
        {
            // arkadaşlık listesi yok oluştur
            friendRelationship = new Relationship();
            friendRelationship.setUserId(userId);
        }

        return friendRelationship;
    }

    private static Plan createPlan()
    {
        Plan plan = new Plan();
        plan.setName("my first plan 2");
        plan.setUniqId(UUID.randomUUID().toString());
        return plan;
    }

    private static User createFriend(DataFactory df)
    {
        User friend = new User();
        friend.setId(UUID.randomUUID().toString());
        friend.setFirstName(df.getFirstName());
        friend.setLastName(df.getLastName());
        friend.setUsername(df.getRandomText(15));
        friend.setPassword(df.getRandomText(10));
        return friend;
    }

    private static User createUser(DataFactory df)
    {
        User user = new User();
        user.setFirstName(df.getFirstName());
        user.setLastName(df.getLastName());
        user.setUsername(df.getRandomText(15));
        user.setPassword(df.getRandomText(10));
        return user;
    }

}
