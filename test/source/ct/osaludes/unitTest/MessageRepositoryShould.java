package ct.osaludes.unitTest;

import ct.osaludes.messages.infrastructure.MessageRepository;
import ct.osaludes.messages.model.TimelineMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MessageRepositoryShould {
    private MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {
        messageRepository = new MessageRepository();
    }

    @Test
    public void createUserAndStoreMessageFirstTime() throws Exception {

        String alias = "john";
        String message = "blablbal";

        messageRepository.add(alias, message);

        List<TimelineMessage> timelineMessages = messageRepository.findAllByAlias(alias);

        assertThat(timelineMessages.size(), is(1));
        assertThat(timelineMessages.get(0), is(timelineMessage(message, "2001/1/1")));
    }

    private TimelineMessage timelineMessage(String message, String date) {
        return new TimelineMessage(message, date);
    }
}

