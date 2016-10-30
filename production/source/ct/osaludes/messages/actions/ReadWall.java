package ct.osaludes.messages.actions;

import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.users.model.FollowingUsersRepository;

import java.util.List;

public class ReadWall {
    private MessageRepository messageRepository;
    private FollowingUsersRepository followingUsersRepository;

    public ReadWall(MessageRepository messageRepository, FollowingUsersRepository followingUsersRepository) {
        this.messageRepository = messageRepository;
        this.followingUsersRepository = followingUsersRepository;
    }

    public List<TimelineMessage> execute(String alias) {
        List<String> followingUsers = followingUsersRepository.findAllByAlias(alias);
        return messageRepository.findAllByAlias(followingUsers);
    }
}
