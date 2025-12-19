package com.kodilla.stream;

import com.kodilla.stream.forumuser.Forum;
import com.kodilla.stream.forumuser.ForumUser;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {

        Random random = new Random();

        // List with random Users
        List<ForumUser> forumUserList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            String userName = "User" + i;
            int randomYear = random.nextInt(25) + 2000;
            int randomMonth = random.nextInt(12) + 1;
            int randomDay = random.nextInt(28) + 1;
            int randomNumberOfPosts = random.nextInt(40);

            if (i%2==0) {
                forumUserList.add(new ForumUser(i, userName, 'M', LocalDate.of(randomYear, randomMonth, randomDay), randomNumberOfPosts));
            }else{
                forumUserList.add(new ForumUser(i, userName, 'F', LocalDate.of(randomYear, randomMonth, randomDay), randomNumberOfPosts));
            }
        }

        Forum forum = new Forum(forumUserList);
        Map<Integer, ForumUser> mapWithUsers = forum.getUserList().stream()
                .filter(forumUser -> forumUser.getSex() == 'M')
                .filter(forumUser -> forumUser.getDateOfBirth().isBefore(LocalDate.now().minusYears(20)))
                .filter(forumUser -> forumUser.getNumberOfPosts() >= 1)
                .collect(Collectors.toMap(ForumUser::getId, userForumUser -> userForumUser));

        mapWithUsers.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .forEach(System.out::println);

    }
}
