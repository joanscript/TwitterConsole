package ct.osaludes.users.model;

import java.util.*;

public class FollowingUsersRepository {

    private Map<String, LinkedHashSet<String>> followingUsers = new HashMap<>();

    public void addFollowingUser(String alias, String followingAlias) {
        LinkedHashSet<String> following = getAliasFollowingUsers(alias);
        following.add(followingAlias);
        followingUsers.put(alias, following);
    }

    public HashSet<String> findAllByAlias(String alias) {
        return followingUsers.get(alias);
    }

    private LinkedHashSet<String> getAliasFollowingUsers(String alias) {
        LinkedHashSet<String> following = followingUsers.get(alias);
        if (following == null) {
            following = new LinkedHashSet<>();
        }
        return following;
    }
}
