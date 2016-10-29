package ct.osaludes.unitTest;

import ct.osaludes.messages.actions.ReadTimeline;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReadTimelineShould {
    @Mock MessageRepository messageRepository = new MessageRepository();
    private ReadTimeline readTimeline;

    @Before
    public void setUp() throws Exception {
        readTimeline = new ReadTimeline(messageRepository);
    }

    @Test
    public void getAllMessagesFromTimelineUser() throws Exception {
        String alias = "john";
        List<TimelineMessage> timelineMessages = asList(new TimelineMessage());
        given(messageRepository.findAllByAlias(alias)).willReturn(timelineMessages);

        List<TimelineMessage> actual = readTimeline.execute(alias);

        Assert.assertEquals(timelineMessages, actual);
    }
}
