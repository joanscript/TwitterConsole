package ct.osaludes.messages.builders;

import ct.osaludes.users.action.FollowUser;
import ct.osaludes.users.model.FollowingUsersRepository;

public class FollowUserStrategy implements ProcessStrategy {
    private final FollowUser followUser;

    public FollowUserStrategy() {
        followUser = new FollowUser(FollowingUsersRepository.getInstance());
    }

    public void execute(String alias, String message) {
        followUser.execute(alias, message);
    }
}
