package ct.osaludes.users.action;

import ct.osaludes.users.model.FollowingUsersRepository;

public class FollowUser {
    private FollowingUsersRepository followingUsersRepository;

    public FollowUser(FollowingUsersRepository followingUsersRepository) {
        this.followingUsersRepository = followingUsersRepository;
    }

    public void execute(String alias, String followingAlias) {
        followingUsersRepository.addFollowingUser(alias, followingAlias);
    }
}
