package ct.osaludes.unitTest;

import ct.osaludes.messages.actions.ReadWall;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.users.model.FollowingUsersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ReadWallShould {
    @Mock MessageRepository messageRepository;
    @Mock FollowingUsersRepository followingUsersRepository;

    private ReadWall readWall;

    @Before
    public void setUp() throws Exception {
        readWall = new ReadWall(messageRepository, followingUsersRepository);
    }

    @Test
    public void returnAllWallEntriesOfAGivenUser() throws Exception {
        String alias = "john";

        String alias1 = "maria";
        String alias2 = "andrea";
        List<String> followingUsers = new ArrayList<>();
        followingUsers.add(alias1);
        followingUsers.add(alias2);
        given(followingUsersRepository.findAllByAlias(alias)).willReturn(followingUsers);

        List<TimelineMessage> timelineMessages = asList(new TimelineMessage());
        given(messageRepository.findAllByAlias(followingUsers)).willReturn(timelineMessages);

        List<TimelineMessage> actual = readWall.execute(alias);

        Assert.assertEquals(timelineMessages, actual);
    }
}
