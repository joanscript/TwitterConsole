package ct.osaludes.acceptanceTest;

import ct.osaludes.messages.actions.PostMessage;
import ct.osaludes.messages.actions.ReadTimeline;
import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import ct.osaludes.messages.ui.Console;
import ct.osaludes.messages.ui.FormatDateTime;
import ct.osaludes.messages.ui.TimelinePrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostAndReadTimelineFeature {
    @Mock Console console;
    @Mock Clock clock;

    @Test
    public void printAllMessagesPostByAUser() throws Exception {
        given(clock.getDateTime()).willReturn("2016/10/30 17:00:00");

        MessageRepository messageRepository = new MessageRepository(clock);
        PostMessage postMessage = new PostMessage(messageRepository);
        String alias = "john";
        String message = "hello world";
        postMessage.post(alias, message);
        ReadTimeline readTimeline = new ReadTimeline(messageRepository);
        List<TimelineMessage> timelineMessages = readTimeline.execute(alias);

        TimelinePrinter timelinePrinter = new TimelinePrinter(console, new FormatDateTime());
        timelinePrinter.print(timelineMessages);

        verify(console).print("hello world 2016/10/30 17:00:00");

    }
}
