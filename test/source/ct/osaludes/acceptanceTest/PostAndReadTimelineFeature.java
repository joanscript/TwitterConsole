package ct.osaludes.acceptanceTest;

import ct.osaludes.messages.actions.PostMessage;
import ct.osaludes.messages.actions.ReadTimeline;
import ct.osaludes.messages.actions.ReadWall;
import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.infrastructure.Console;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.TimelinePrinter;
import ct.osaludes.messages.ui.WallPrinter;
import ct.osaludes.users.action.FollowUser;
import ct.osaludes.users.model.FollowingUsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostAndReadTimelineFeature {
    @Mock Console console;
    @Mock Clock clock;

    @Test
    public void printAllMessagesPostByAUser() throws Exception {
        when(clock.getDateTime()).thenReturn("2016/10/30 17:00:00", "2016/10/30 17:03:00");

        MessageRepository messageRepository = new MessageRepository(clock);
        PostMessage postMessage = new PostMessage(messageRepository);
        String alias = "john";
        String message = "hello world";
        postMessage.execute(alias, message);
        ReadTimeline readTimeline = new ReadTimeline(messageRepository);
        List<TimelineMessage> timelineMessages = readTimeline.execute(alias);

        TimelinePrinter timelinePrinter = new TimelinePrinter(console, new FormatDateTime(clock));
        timelinePrinter.print(timelineMessages);

        verify(console).print("hello world (3 minutes ago)");
    }

    @Test
    public void printWallByAUser() throws Exception {
        when(clock.getDateTime()).thenReturn("2016/10/30 17:00:00", "2016/10/30 17:02:00", "2016/10/30 17:05:00", "2016/10/30 17:05:01");

        MessageRepository messageRepository = new MessageRepository(clock);
        PostMessage postMessage = new PostMessage(messageRepository);
        String alias = "john";
        String message = "hello world";
        String alias2 = "maria";
        String message2 = "my first message";
        postMessage.execute(alias, message);
        postMessage.execute(alias2, message2);

        FollowingUsersRepository followingUsersRepository = new FollowingUsersRepository();
        FollowUser followUser = new FollowUser(followingUsersRepository);
        followUser.execute(alias, alias2);

        ReadWall readWall = new ReadWall(messageRepository, followingUsersRepository);
        List<TimelineMessage> wallMessages = readWall.execute(alias);

        WallPrinter wallPrinter = new WallPrinter(console, new FormatDateTime(clock));
        wallPrinter.print(wallMessages);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("maria - my first message (3 minutes ago)");
        inOrder.verify(console).print("john - hello world (5 minutes ago)");
    }

    @Test
    public void followUserAndPrintWallByAUser() throws Exception {
        given(clock.getDateTime()).willReturn("2016/10/30 17:00:00");

        MessageRepository messageRepository = new MessageRepository(clock);
        PostMessage postMessage = new PostMessage(messageRepository);
        String alias = "john";
        String message = "hello world";
        postMessage.execute(alias, message);

        ReadWall readWall = new ReadWall(messageRepository, new FollowingUsersRepository());
        List<TimelineMessage> wallMessages = readWall.execute(alias);

        WallPrinter wallPrinter = new WallPrinter(console, new FormatDateTime(clock));
        wallPrinter.print(wallMessages);

        verify(console).print("john - hello world (less than 1 minute ago)");
    }
}
