package ct.osaludes.unitTest;

import ct.osaludes.messages.infrastructure.Clock;
import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
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
        assertThat(timelineMessages.get(0), is(timelineMessage(alias, message, dateTime)));
    }

    @Test
    public void createUserAndStoreSeveralMessages() throws Exception {
        String alias = "john";
        String message1 = "blablbal1";
        String message2 = "blablbal2";
        String message3 = "blablbal3";
        given(clock.getDateTime()).willReturn(dateTime);
        messageRepository.add(alias, message1);
        messageRepository.add(alias, message2);
        messageRepository.add(alias, message3);

        List<TimelineMessage> timelineMessages = messageRepository.findAllByAlias(alias);

        assertThat(timelineMessages.size(), is(3));
        assertThat(timelineMessages.get(0), is(timelineMessage(alias, message1, dateTime)));
        assertThat(timelineMessages.get(1), is(timelineMessage(alias, message2, dateTime)));
        assertThat(timelineMessages.get(2), is(timelineMessage(alias, message3, dateTime)));
    }

    @Test
    public void createSeveralUsersAndStoreSeveralMessages() throws Exception {
        String alias = "john";
        String alias1 = "maria";
        String message = "blablbal";
        given(clock.getDateTime()).willReturn(dateTime);
        messageRepository.add(alias, message);
        messageRepository.add(alias1, message);
        messageRepository.add(alias, message);

        assertThat(messageRepository.findAllByAlias(alias).size(), is(2));
        assertThat(messageRepository.findAllByAlias(alias1).size(), is(1));
    }

    private TimelineMessage timelineMessage(String alias, String message, String date) {
        return new TimelineMessage(alias, message, date);
    }
}

