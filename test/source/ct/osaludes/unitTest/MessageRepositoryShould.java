package ct.osaludes.unitTest;

import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MessageRepositoryShould {
    @Mock Clock clock;

    private MessageRepository messageRepository;
    private static final String dateTime = "2001/1/1";

    @Before
    public void setUp() throws Exception {
        messageRepository = new MessageRepository(clock);
    }

    @Test
    public void createUserAndStoreMessageFirstTime() throws Exception {
        String alias = "john";
        String message = "blablbal";
        given(clock.getDateTime()).willReturn(dateTime);
        messageRepository.add(alias, message);

        List<TimelineMessage> timelineMessages = messageRepository.findAllByAlias(alias);

        assertThat(timelineMessages.size(), is(1));
        assertThat(timelineMessages.get(0), is(timelineMessage(message, dateTime)));
    }

    private TimelineMessage timelineMessage(String message, String date) {
        return new TimelineMessage(message, date);
    }
}

