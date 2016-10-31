package ct.osaludes.users.model;

import java.util.*;

public class FollowingUsersRepository {
    private static FollowingUsersRepository instance = null;

    private Map<String, LinkedHashSet<String>> followingUsers = new HashMap<>();

    public static FollowingUsersRepository getInstance() {
        if (instance == null) {
            instance = new FollowingUsersRepository();
        }
        return instance;
    }

    public void addFollowingUser(String alias, String followingAlias) {
        LinkedHashSet<String> following = getAliasFollowingUsers(alias);
        following.add(followingAlias);
        followingUsers.put(alias, following);
    }

    public LinkedHashSet<String> findAllByAlias(String alias) {
        return getAliasFollowingUsers(alias);
    }

    private LinkedHashSet<String> getAliasFollowingUsers(String alias) {
        LinkedHashSet<String> following = followingUsers.get(alias);
        if (following == null) {
            following = new LinkedHashSet<>();
        }
        return following;
    }
}
