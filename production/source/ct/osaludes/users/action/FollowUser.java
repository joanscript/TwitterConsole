package ct.osaludes.users.action;

import ct.osaludes.users.model.FollowersRepository;

public class FollowUser {
    private FollowersRepository followersRepository;

    public FollowUser(FollowersRepository followersRepository) {
        this.followersRepository = followersRepository;
    }

    public void execute(String alias, String followingAlias) {
        followersRepository.addFollowingUser(alias, followingAlias);
    }
}
