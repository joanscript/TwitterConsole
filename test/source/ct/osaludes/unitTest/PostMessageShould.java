package ct.osaludes.unitTest;

import ct.osaludes.messages.actions.PostMessage;
import ct.osaludes.messages.infrastructure.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostMessageShould {
    @Mock MessageRepository messageRepository;
    private PostMessage postMessage;

    @Before
    public void initialize() {
        postMessage = new PostMessage(messageRepository);
    }

    @Test
    public void postMessageToPersistance() {
        String alias = "john";
        String message = "blablbala";
        postMessage.post(alias, message);

        verify(messageRepository).add(alias, message);
    }
}