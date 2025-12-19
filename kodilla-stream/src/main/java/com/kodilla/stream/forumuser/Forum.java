package com.kodilla.stream.forumuser;

import java.util.ArrayList;
import java.util.List;

public final class Forum {

    private final List<ForumUser> userList;

    public Forum(final List<ForumUser> userList) {
        this.userList = userList;
    }

    public List<ForumUser> getUserList() {
        return new ArrayList<>(userList);
    }
}
